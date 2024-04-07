package com.example.bookapp;

import android.util.Log;
import android.view.View;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.bookapp.model.Author;
import com.example.bookapp.model.Book;
import com.example.bookapp.model.Comment;
import com.example.bookapp.model.Tag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class APIRequest {

    String apiBaseName = "";
    View view;

    public APIRequest() {
        this.view = view;
        this.apiBaseName = "http://192.168.123.131:3000";
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

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject author = response.getJSONObject(i); // Obtenir l'élément JSON à l'index i
                                String firstname = author.getString("firstname");
                                String lastname = author.getString("lastname");
                                int id = author.getInt("id");
                                Author newAuthor = new Author(id, firstname, lastname);
                                Log.d("VolleyResponse", "Auteur : "+newAuthor.getFirstname()+" "+newAuthor.getLastname());
                                //books
                                JSONArray booksArray = author.getJSONArray("books");
                                for (int j = 0; j < booksArray.length(); j++) {
                                    JSONObject bookObj = booksArray.getJSONObject(j);
                                    int bookId = bookObj.getInt("id");
                                    int date = bookObj.getInt("date");
                                    String bookTitle = bookObj.getString("title");
                                    newAuthor.getBooks().add(new Book(bookId, bookTitle, newAuthor,date));
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
                                int date = book.getInt("date");
                                Author author = new Author(book.getJSONObject("author").getInt("id"),
                                        book.getJSONObject("author").getString("firstname"),
                                        book.getJSONObject("author").getString("lastname"));


                                Book newBook = new Book(id, title, author,date);
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

    public JsonArrayRequest getTagsofBook(MutableLiveData<List<Book>> res, Book book) { // pour récupérer les tags d'un livre

        String url = apiBaseName+"/books/"+book.getId()+"/tags";
        ArrayList<Tag> tags = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject tag = response.getJSONObject(i);
                                String name = tag.getString("name");
                                int id = tag.getInt("id");
                                Tag newTag = new Tag(id, name);
                                tags.add(newTag);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        book.setTags(tags);

                        List<Book> bookList = res.getValue();
                        for (int i = 0; i < bookList.size(); i++) {
                            if (bookList.get(i).getId() == book.getId()) {
                                bookList.set(i, book);
                                res.setValue(bookList);
                                break;
                            }
                        }



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

    public JsonArrayRequest getTags(MutableLiveData<List<Tag>> res) {

        List<Tag> tags = new ArrayList<>();

        String url = apiBaseName+"/tags";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject tagList = response.getJSONObject(i);

                                Tag newTag = new Tag(tagList.getInt("id"), tagList.getString("name"));
                                Log.d("tags",newTag.getName() );
                                tags.add(newTag);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        res.setValue(tags);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gérer les erreurs de requête
                        Log.e("VolleyError", "Erreur lors de la requête", error);
                    }
                });

        return jsonArrayRequest;
    }

    public JsonArrayRequest getComments(MutableLiveData<List<Comment>> res) {

        List<Comment> comments = new ArrayList<>();

        String url = apiBaseName+"/comments";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject commentList = response.getJSONObject(i);

                                //Tag newTag = new Tag(commentList.getInt("id"), commentList.getString("name"));
                                //Log.d("tags",newTag.getName() );

                                Comment newComment = new Comment(commentList.getInt("id"),
                                        commentList.getString("content"),
                                        //LocalDateTime.parse(commentList.getString("createdAt")),
                                        //LocalDateTime.parse(commentList.getString("updatedAt")),
                                        commentList.getString("username"));
                                comments.add(newComment);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        res.setValue(comments);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gérer les erreurs de requête
                        Log.e("VolleyError", "Erreur lors de la requête", error);
                    }
                });

        return jsonArrayRequest;
    }

    public JsonArrayRequest getCommentsOfBook(Book book) {

        List<Comment> comments = new ArrayList<>();

        String url = apiBaseName+"/books/"+book.getId()+"/comments";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        ArrayList<Comment> tempListComment = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject commentList = response.getJSONObject(i);
                                Comment newComment = new Comment(commentList.getInt("id"),
                                        commentList.getString("content"),
                                        //LocalDateTime.parse(commentList.getString("createdAt")),
                                        //LocalDateTime.parse(commentList.getString("updatedAt")),
                                        commentList.getString("username"));
                                        //commentList.getDouble("rating"));
                                tempListComment.add(newComment);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        book.setComments(tempListComment);
                        //res.setValue(comments);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gérer les erreurs de requête
                        Log.e("VolleyError", "Erreur lors de la requête", error);
                    }
                });

        return jsonArrayRequest;
    }
    public JsonArrayRequest getRatingOfBook(Book book) {

        String url = apiBaseName+"/books/"+book.getId()+"/rating";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Traitement de la réponse JSON
                        // Par exemple, afficher la réponse dans la console
                        double sumRating = 0;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject rating = response.getJSONObject(i);
                                sumRating += rating.getInt("value");
                                for(int y=0;y<book.getComments().size();y++){
                                    if(book.getComments().get(y).getUserName().equals(rating.getString("username"))){
                                        book.getComments().get(y).setRating(rating.getInt("value"));
                                        break;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        book.setRating(sumRating/response.length());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Gérer les erreurs de requête
                        Log.e("VolleyError", "Erreur lors de la requête", error);
                    }
                });

        return jsonArrayRequest;
    }

    public JsonObjectRequest addAuthor(String firstname, String lastname) {
        String url = apiBaseName + "/authors";

        JSONObject postData = new JSONObject();
        try {
            postData.put("firstname", firstname);
            postData.put("lastname", lastname);
            // Ajouter d'autres paramètres au besoin
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Traitement de la réponse
                        // Par exemple, afficher la réponse dans la console
                        Log.d("Add", "onResponse: "+response.toString());
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

    public JsonObjectRequest addBook(int authorId, String title, int date) {
        String url = apiBaseName + "/authors/"+authorId+"/books";

        JSONObject postData = new JSONObject();
        try {
            postData.put("title", title);
            postData.put("date", date);
            // Ajouter d'autres paramètres au besoin
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Traitement de la réponse
                        // Par exemple, afficher la réponse dans la console
                        Log.d("Add Book", "onResponse: "+response.toString());
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

    public StringRequest deleteAuthor(int id) {
        String url = apiBaseName + "/authors/"+id;

        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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

    public StringRequest deleteBook(int id) {
        String url = apiBaseName + "/books/"+id;

        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
