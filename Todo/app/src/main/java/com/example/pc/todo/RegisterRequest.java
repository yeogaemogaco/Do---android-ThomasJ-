package com.example.pc.todo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 2017-08-18.
 */

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://ruddmsdl000.cafe24.com/register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String id, String password,String name, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("id",id);
        parameters.put("password",password);
        parameters.put("name",name);



    }
    @Override
    public Map<String, String> getParams() {
        return parameters;
    }



}
