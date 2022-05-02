package com.example.xdworkouttracker.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.xdworkouttracker.AlarmDataFileHelper;
import com.example.xdworkouttracker.R;
import com.example.xdworkouttracker.databinding.FragmentProfileBinding;
import com.example.xdworkouttracker.ui.process.Process;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileFragment extends Fragment {
    private BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelsNames;
    private ArrayList<Process> processArrayList;
    private HashMap processData = new HashMap();
    private FragmentProfileBinding binding;
    private SharedPreferences sp;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textProfile;
        getUserInfo();
        drawBarChart();
        drawCalendar();
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    private void drawBarChart(){
        getProcesses();
        barChart = binding.processBarChart;
        // create new object for bar entry array list
        barEntryArrayList = new ArrayList<>();
        labelsNames = new ArrayList<>();

        int i = 0;
        for(Object k: processData.keySet()){
            barEntryArrayList.add(
                    new BarEntry(i, (Float) processData.get(k))
            );
            labelsNames.add(String.valueOf(k));
            i++;
        }
        // Input the data
        BarDataSet barDataSet = new BarDataSet(barEntryArrayList, "Sports Type");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setTextColor(Color.BLUE);
        description.setText("Total Calories Consumption");
        description.setPosition(1080.0f, 665.0f);
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        // Set X-axis
        XAxis x = barChart.getXAxis();
        x.setLabelCount(labelsNames.size(), false);
        x.setValueFormatter(new IndexAxisValueFormatter(labelsNames));
        // Set position
        x.setPosition(XAxis.XAxisPosition.TOP);


    }

    private void getProcesses(){
        processArrayList = AlarmDataFileHelper.getProcessStorage();
        for(Process p: processArrayList){
            String key = p.getActivities();
            Float value = Float.parseFloat(p.getResult().equals("")?"0.0f": p.getResult());
            if(processData.containsKey(key)){
                processData.put(key, Float.parseFloat(processData.get(key).toString()) + value);
            }
            else{
                processData.put(key, value);
            }
        }
    }
    private void getUserInfo(){
        TextView username = binding.profUsername;
        TextView height = binding.profHeight;
        TextView weight = binding.profWeight;

        sp = getContext().getApplicationContext().getSharedPreferences("UserCookie", Context.MODE_PRIVATE);
        username.setText(sp.getString("USERNAME", null ));
        height.setText(String.valueOf(sp.getFloat("HEIGHT", 0.0f )));
        weight.setText(String.valueOf(sp.getFloat("WEIGHT",0.0f )));
    }

    private void drawCalendar(){
        List<EventDay> events = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate currentdate = LocalDate.now();
        }
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, month, day);
        events.add(new EventDay(calendar, R.drawable.ic_baseline_fitness_center_24));
        CalendarView calendarView = (CalendarView) binding.calendarView;
        calendarView.setEvents(events);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}