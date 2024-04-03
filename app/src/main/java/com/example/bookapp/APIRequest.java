package com.example.bookapp;

import android.util.Log;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.bookapp.ui.authors.Author;
import com.example.bookapp.ui.home.Book;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIRequest {

    String apiBaseName = "";
    View view;

    public APIRequest() {
        this.view = view;
        this.apiBaseName = "http://192.168.3.224:3000";
    }

    public JsonArrayRequest getAuthors(MutableLiveData<List<Author>> res) {

        List<Author> myList = new ArrayList<>();



        String url = apiBaseName+"/authors?include=book";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        //Log.d("VolleyResponse", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject author = response.getJSONObject(i); // Obtenir l'élément JSON à l'index i
                                String firstname = author.getString("firstname");
                                String lastname = author.getString("lastname");
                                int id = author.getInt("id");
                                Author newAuthor = new Author(id, firstname, lastname);
                                //books
                                JSONArray booksArray = author.getJSONArray("books");
                                for (int j = 0; j < booksArray.length(); j++) {
                                    JSONObject bookObj = booksArray.getJSONObject(j);
                                    int bookId = bookObj.getInt("id");
                                    String bookTitle = bookObj.getString("title");
                                    newAuthor.getBooks().add(new Book(bookId, bookTitle, newAuthor));
                                }
                                myList.add(newAuthor); // Ajouter le nom de l'auteur à la liste

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        res.setValue(myList);
                        //res.setValue(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gérer les erreurs de requête
                        Log.e("VolleyError", "Erreur lors de la requête", error);
                    }
                });

        return jsonArrayRequest;

        // Ajouter la requête à la file de requêtes
    }
    public JsonArrayRequest getBooks(MutableLiveData<List<Book>> res) { // pour récupérer les livres

        List<Book> books = new ArrayList<>();


        String url = apiBaseName+"/books?include=author";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        //Log.d("VolleyResponse book", response.toString()+" "+ response.length());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject book = response.getJSONObject(i);
                                int id = book.getInt("id");
                                String title = book.getString("title");
                                Author author = new Author(book.getJSONObject("author").getInt("id"), book.getJSONObject("author").getString("firstname"),book.getJSONObject("author").getString("lastname"));
                                Book newBook = new Book(id, title, author);
                                books.add(newBook);// Ajouter les informations du livre
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        res.setValue(books);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gérer les erreurs de requête
                        Log.e("VolleyError", "Erreur lors de la requête", error);
                    }
                });

        return jsonArrayRequest;

        // Ajouter la requête à la file de requêtes
    }

    public StringRequest addAuthor(String firstname, String lastname) {
        String url = apiBaseName + "/authors";

        JSONObject postData = new JSONObject();
        try {
            postData.put("firstname", firstname);
            postData.put("lastname", lastname);
            // Ajouter d'autres paramètres au besoin
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Traitement de la réponse
                        // Par exemple, afficher la réponse dans la console
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Gérer les erreurs de requête
                Log.e("Erreur de requête", error.toString());
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return postData.toString().getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Ajouter la requête à la file de requêtes
        return request;
    }

    public StringRequest deleteAuthor(int id) {
        String url = apiBaseName + "/authors/"+id;

        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Traitement de la réponse
                        // Par exemple, afficher la réponse dans la console
                        //Log.d("Réponse de l'API", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Gérer les erreurs de requête
                Log.e("Erreur de requête", error.toString());
            }
        });

        // Ajouter la requête à la file de requêtes
        return request;
    }





}
