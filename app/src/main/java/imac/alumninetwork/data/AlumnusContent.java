package imac.alumninetwork.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

/**
 * Created by Monia on 12/03/15.
 */
public class AlumnusContent {

    public  List<Alumni> ALUMNUS ;
    private JSONArray jsonArray;
    private Map<String, Alumni> SEARCH ;

    static {



    /*
        ALUMNUS.add(new Alumni("Alumni A", "Company A", "Programming", "2000","a@gmail.com", "0123456789"));
        ALUMNUS.add(new Alumni("Alumni B", "Company B", "Audiovisual", "2001","b@gmail.com", "0789456123"));
        ALUMNUS.add(new Alumni("Alumni C", "Company C", "Web", "2002","c@gmail.com", "0123456789"));
        ALUMNUS.add(new Alumni("Alumni D", "Company D", "Programming", "2003","d@gmail.com", "0789123456"));
        ALUMNUS.add(new Alumni("Alumni E", "Company A", "Audiovisual", "2004","e@gmail.com", "0147852369"));
        ALUMNUS.add(new Alumni("Alumni F", "Company B", "Web", "2005","f@gmail.com", "0369852147"));
        ALUMNUS.add(new Alumni("Alumni G", "Company C", "Programming", "2006","g@gmail.com", "0459876321"));
        ALUMNUS.add(new Alumni("Alumni H", "Company D", "Audiovisual", "2007","h@gmail.com", "0178945623"));
        ALUMNUS.add(new Alumni("Alumni I", "Company A", "Web", "2008","i@gmail.com", "0354789621"));
        ALUMNUS.add(new Alumni("Alumni J", "Company B", "Programming", "2009","j@gmail.com", "0978465132"));
        ALUMNUS.add(new Alumni("Alumni K", "Company C", "Audiovisual", "2010","k@gmail.com", "0258749631"));
        ALUMNUS.add(new Alumni("Alumni L", "Company D", "Web", "2011","l@gmail.com", "0345687921"));
        ALUMNUS.add(new Alumni("Alumni M", "Company A", "Programming", "2012","m@gmail.com", "0819325647"));
        ALUMNUS.add(new Alumni("Alumni N", "Company B", "Audiovisual", "2013","n@gmail.com", "0172894563"));
        ALUMNUS.add(new Alumni("Alumni O", "Company C", "Web", "2014","o@gmail.com", "0976435128"));
        ALUMNUS.add(new Alumni("Alumni P", "Company D", "Programming", "2015","p@gmail.com", "081579463"));*/

    }

    public AlumnusContent(){

        ALUMNUS = new ArrayList<Alumni>();
        //new GetAllAlumniTask().execute(new ApiConnector());
        jsonArray = new ApiConnector().GetAllAlumni();

        try {

            for(int i =0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ALUMNUS.add(new Alumni(jsonObject.getString("name"), jsonObject.getString("company"), jsonObject.getString("field"),
                        jsonObject.getString("promo"),jsonObject.getString("email"), jsonObject.getString("phoneNumber")));


            }
            //ALUMNUS = helper.toList(jsonArray);
        }
        catch (JSONException e) { }


        SEARCH = new HashMap<String, Alumni>();

        for(Alumni alumni: ALUMNUS){
            SEARCH.put(alumni.getName(), alumni);
        }
    }

    public  List<Alumni> findAlumnusByCompanyAndField(String company, String field){
        List<Alumni> tmpAlumnus = new ArrayList<Alumni>();
        for(Alumni alumni: ALUMNUS){
            if (alumni.getField().equals(field) && alumni.getCompany().equals(company)) {
                tmpAlumnus.add(alumni);
            }
        }
        return tmpAlumnus;
    }

    public Alumni findAlumni (String name){
        return SEARCH.get(name);
    }


    private class GetAllAlumniTask extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].GetAllAlumni();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            JsonHelper helper = new JsonHelper();
            try {

                for(int i =0; i< jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ALUMNUS.add(new Alumni(jsonObject.getString("name"), jsonObject.getString("company"), jsonObject.getString("field"),
                            jsonObject.getString("promo"),jsonObject.getString("email"), jsonObject.getString("phoneNumber")));


                }
                //ALUMNUS = helper.toList(jsonArray);
            }
            catch (JSONException e) { }

        }
    }






}
