package org.tchtown.baewhaaplication;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.tchtown.baewhaaplication.databinding.ActivityMainBinding;
import org.tchtown.baewhaaplication.ui.dashboard.DashboardFragment;
import org.tchtown.baewhaaplication.ui.home.HomeFragment;
import org.tchtown.baewhaaplication.ui.mypage.mypageFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * Attempt to invoke virtual method 'androidx.fragment.app.FragmentTransaction androidx.fragment.app.FragmentManager.beginTransaction()' on a null object reference
 * 오류 해결하기
 */
public class CalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    MainActivity mainActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;

    private int index = 0;
    int nod = 400;
    int days[] = new int[nod];
    int months[] = new int[nod];
    int years[] = new int[nod];

    CalendarView calendarView;
    Button saveButton;
    EditText textInput;
    List<String> calendarString = new ArrayList<>();


    final String TAG = this.getClass().getSimpleName();
    private ActivityMainBinding binding;

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    public static String userID;
    private BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    DashboardFragment dashboardFragment;
    org.tchtown.baewhaaplication.ui.mypage.mypageFragment mypageFragment;
    private MenuItem item;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"캘린더 앱 실행");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_mypage)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_view);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        */

        ///* bottom navigation view 추가 20221011 PM10:28
        // bottom navigation view
        homeFragment = new HomeFragment();
        dashboardFragment = new DashboardFragment();
        mypageFragment = new mypageFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.notice, homeFragment).commit();
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.notice, dashboardFragment).commit();
                        return true;
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.notice, homeFragment).commit();
                        return true;
                    case R.id.navigation_mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.notice, mypageFragment).commit();
                        return true;
                }
                return false;
            }
        });




        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //*/
    }
// 추가아ㅏ아
    private BottomNavigationView findViewById(int nav_view) {
        return null;
    }

    private void setContentView(LinearLayout root) {
    }
// 추가아아아
    private FragmentManager getSupportFragmentManager() {
        return null;
    }


    private void setRequestedOrientation(int screenOrientationPortrait) {
    }

    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        calendarView = (CalendarView) getView().findViewById(R.id.calendarView);
        saveButton = (Button) getView().findViewById(R.id.saveButton);
        textInput = (EditText)getView().findViewById(R.id.textInput);

        readInfo();

        // 달력 넘기기 + 일 선택 기능
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(String.valueOf(CalendarFragment.this));
                startActivity(intent);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());
                fragmentTransaction.commit();
            }
        });

        // 달력 넘김 시 날짜 변경 기능
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1+1)+"/"+i2+"/"+i;
                currentYear = i;
                currentMonth = i1;
                currentDay = i2;
                for(int l=0;l<index;l++){
                    if(years[l] == currentYear){
                        for(int k=0;k<index;k++){
                            if(days[k] == currentDay){
                                for(int j=0;j<index;j++){
                                    if(months[j] == currentMonth && days[j] == currentDay && years[j] == currentYear){
                                        textInput.setText(calendarString.get(j));
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
                textInput.setText("");
            }
        });

        // 저장 버튼 이벤트 처리 기능 --> 임시로 기능 확인만을 위해서 작성함
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // calendarString에 입력한 textInput저장
                days[index] = currentDay;
                months[index] = currentMonth;
                years[index] = currentYear;
                calendarString.add(index, textInput.getText().toString());
                index++;
                // 일정 저장 시 입력하는 곳 초기화
                textInput.setText("");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog dialog = builder.setMessage("저장되었습니다.")
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();
            }
        });

        new BackgroundTask().execute();
    }
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://jsw0101151.cafe24.com/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }  catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            //noticeListView.setAdapter(adapter);
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String noticeContent, noticeName, noticeDate;
                while (count < jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count);
                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeName");
                    noticeDate = object.getString("noticeDate");
                    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    noticeList.add(notice);
                    adapter.notifyDataSetChanged();
                    count++;
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private long lastTimeBackPressed;

    @Override
    public void onPause(){
        super.onPause();
        saveInfo();
    }

    public void saveInfo(){
        File file = new File(getContext().getFilesDir(), "saved");
        File dFile = new File(getContext().getFilesDir(), "days");
        File mFile = new File(getContext().getFilesDir(), "month");
        File yFile = new File(getContext().getFilesDir(), "years");

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fOut));

            FileOutputStream fOutDays = new FileOutputStream(dFile);
            BufferedWriter bwDays = new BufferedWriter(new OutputStreamWriter(fOutDays));

            FileOutputStream fOutMonths = new FileOutputStream(mFile);
            BufferedWriter bwMonths = new BufferedWriter(new OutputStreamWriter(fOutMonths));

            FileOutputStream fOutYears = new FileOutputStream(yFile);
            BufferedWriter bwYears = new BufferedWriter(new OutputStreamWriter(fOutYears));

            for(int i=0; i<index;i++){
                bw.write(calendarString.get(i));
                bw.newLine();
                bwDays.write(days[i]);
                bwMonths.write(months[i]);
                bwYears.write(years[i]);
            }

            bw.close();
            fOut.close();
            bwDays.close();
            fOutDays.close();
            bwMonths.close();
            fOutMonths.close();
            bwYears.close();
            fOutYears.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readInfo(){
        File file = new File(getContext().getFilesDir(), "saved");
        File dFile = new File(getContext().getFilesDir(), "days");
        File mFile = new File(getContext().getFilesDir(), "month");
        File yFile = new File(getContext().getFilesDir(), "years");

        if(!file.exists()){
            return;
        }
        try{
            FileInputStream is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            FileInputStream isDays = new FileInputStream(dFile);
            BufferedReader rDays = new BufferedReader(new InputStreamReader(isDays));
            FileInputStream isMonths = new FileInputStream(mFile);
            BufferedReader rMonths = new BufferedReader(new InputStreamReader(isMonths));
            FileInputStream isYears = new FileInputStream(yFile);
            BufferedReader rYears = new BufferedReader(new InputStreamReader(isYears));

            int i = 0;
            String line = reader.readLine();

            while(line!=null){
                calendarString.add(line);
                line = reader.readLine();
                days[i] = rDays.read();
                months[i] = rMonths.read();
                years[i] = rYears.read();
                i++;
            }
            index = i;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}