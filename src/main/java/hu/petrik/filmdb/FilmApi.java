package hu.petrik.filmdb;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class FilmApi {

    private static final String BASE_URL = "http://127.0.0.1:8000/";
    private static final String FILM_API_URL = BASE_URL+"api/filmek";

    public static List<Film> getFilmek() throws IOException {
        Response response = RequestHandler.get(FILM_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        System.out.println(response.responseCode);
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Film>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }

    public static Film filmHozzaadasa(Film ujFilm) throws IOException {
        Gson jsonConvert = new Gson();
        String filmJson = jsonConvert.toJson(ujFilm);
        Response response = RequestHandler.post(FILM_API_URL, filmJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Film.class);
    }

    public static Film filmModositasa(Film modositando) throws IOException {
        Gson jsonConvert = new Gson();
        String filmJson = jsonConvert.toJson(modositando);
        Response response = RequestHandler.put(FILM_API_URL+"/"+modositando.getId(), filmJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Film.class);
    }

    public static boolean filmTorlese(int id) throws IOException {
        Response response = RequestHandler.delete(FILM_API_URL + "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
