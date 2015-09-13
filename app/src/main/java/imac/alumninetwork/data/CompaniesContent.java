package imac.alumninetwork.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Monia on 12/13/15.
 */
public class CompaniesContent {


    public List<Company> COMPANIES ;
    private Map<String, Company> SEARCH ;
    private JSONArray jsonArray;


    public CompaniesContent() {
        COMPANIES = new ArrayList<Company>();
        //new GetAllCompaniesTask().execute(new ApiConnector());
        jsonArray = new ApiConnector().GetAllCompanies();

        try {
            Log.e("Size Json ARRAY", Integer.toString(jsonArray.length()));
            for(int i =0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                COMPANIES.add(new Company(jsonObject.getString("name"), jsonObject.getString("field"), jsonObject.getString("description"),
                        jsonObject.getString("website")));

            }
            //ALUMNUS = helper.toList(jsonArray);
        }
        catch (JSONException e) { }

        Log.e("Company list: ", Integer.toString(COMPANIES.size()));
        SEARCH = new HashMap<String, Company>();

        for(Company company: COMPANIES){
            SEARCH.put(company.getName(), company);
        }


    }

    static {



        /*
        COMPANIES.add(new Company("Company A", "Programming", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","www.a.com"));
        COMPANIES.add(new Company("Company B", "Audiovisual", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb","www.b.com"));
        COMPANIES.add(new Company("Company C", "Web", "ccccccccccccccccccccccccccccccccccccccccccc","www.c.com"));
        COMPANIES.add(new Company("Company D", "Programming", "ddddddddddddddddddddddddddddddddddddddddddd","www.d.com"));
        COMPANIES.add(new Company("Company E", "Audiovisual", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee","www.e.com"));
        COMPANIES.add(new Company("Company F", "Web", "fffffffffffffffffffffffffffffffffffffffffff","www.f.com"));
        COMPANIES.add(new Company("Company G", "Programming", "ggggggggggggggggggggggggggggggggggggggggggg","www.g.com"));
        COMPANIES.add(new Company("Company H", "Audiovisual", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","www.h.com"));
        COMPANIES.add(new Company("Company I", "Web", "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii","www.i.com"));
        COMPANIES.add(new Company("Company J", "Programming", "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj","www.j.com"));
        COMPANIES.add(new Company("Company K", "Audiovisual", "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk","www.k.com"));
        COMPANIES.add(new Company("Company L", "Web", "lllllllllllllllllllllllllllllllllllllllllll","www.l.com"));
        COMPANIES.add(new Company("Company M", "Programming", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm","www.m.com"));
        COMPANIES.add(new Company("Company N", "Audiovisual", "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn","www.n.com"));
        COMPANIES.add(new Company("Company O", "Web", "ooooooooooooooooooooooooooooooooooooooooooo","www.o.com"));
        COMPANIES.add(new Company("Company P", "Programming", "ppppppppppppppppppppppppppppppppppppppppppp","www.p.com")); */
    }


    public List<Company> findCompaniesByField (String field){
        List<Company> tmpCompanies = new ArrayList<Company>();
        for(Company company: COMPANIES){
            if (company.getField().equals(field)) {
                tmpCompanies.add(company);
            }
        }
        return tmpCompanies;
    }

    public Company findCompany (String name){
        return SEARCH.get(name);
    }


}
