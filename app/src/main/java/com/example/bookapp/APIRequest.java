package com.example.bookapp;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.bookapp.ui.authors.Author;
import com.example.bookapp.ui.home.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class APIRequest {

    public APIRequest() {

    }

    public JsonArrayRequest get(MutableLiveData<List<Author>> res) {

        List<Author> myList = new ArrayList<>();


        String url = "http://192.168.1.93:3000/authors";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        Log.d("VolleyResponse", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject author = response.getJSONObject(i); // Obtenir l'élément JSON à l'index i
                                String firstname = author.getString("firstname");
                                String lastname = author.getString("lastname");
                                int id = author.getInt("id");
                                myList.add(new Author(id, firstname, lastname)); // Ajouter le nom de l'auteur à la liste
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


        String url = "http://192.168.1.93:3000/authors";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        Log.d("VolleyResponse", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject book = response.getJSONObject(i); // Obtenir l'élément JSON à l'index i
                                int id = book.getInt("id");
                                String title = book.getString("title");
                                books.add(new Book(id,title)); // Ajouter les informations du livre
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        res.setValue(books);
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

}
