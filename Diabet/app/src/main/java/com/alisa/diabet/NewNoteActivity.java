package com.alisa.diabet;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alisa.diabet.Adapters.SpinnerAdapter;
import com.alisa.diabet.Adapters.SpinnerAdapterEating;
import com.alisa.diabet.Fragments.DatePickerFragment;
import com.alisa.diabet.Fragments.TimePickerFragment;
import com.alisa.diabet.Model.Eating;
import com.alisa.diabet.Model.Insuline;
import com.lantouzi.wheelview.WheelView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewNoteActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_ID =
            "com.alisa.diabet.EXTRA_ID";
    public static final String EXTRA_SUGAR =
            "com.alisa.diabet.EXTRA_SUGAR";
    public static final String EXTRA_DATA =
            "com.alisa.diabet.EXTRA_DATA";
    public static final String EXTRA_DESCRIPTION =
            "com.alisa.diabet.EXTRA_DESCRIPTION";
    public static final String EXTRA_TIME =
            "com.alisa.diabet.EXTRA_TIME";
    public static final String EXTRA_EATING =
            "com.alisa.diabet.EXTRA_EATING";
    public static final String EXTRA_INSULINLEN =
            "com.alisa.diabet.EXTRA_INSULINLEN";
    public static final String EXTRA_INJECETED =
            "com.alisa.diabet.EXTRA_INJECETED";
    public static final String EXTRA_WEIGHT =
            "com.alisa.diabet.EXTRA_WEIGHT";
    public static final String EXTRA_WELLBEING =
            "com.alisa.diabet.EXTRA_WELLBEING";
    public static final String EXTRA_BREAD =
            "com.alisa.diabet.EXTRA_BREAD";
    public static final String EXTRA_PRIORITY =
            "com.alisa.diabet.EXTRA_PRIORITY";


    private Toolbar toolbar;
    WheelView wheelView;
    double sugar_number = 0.0;
    TextView textSugar, textgiper;
    String eats;
    private TextView editTextSugar;
    private EditText editTextData;
    private EditText editTextTime;
    private EditText editTexIns;
    private EditText editTexWeight;
    private EditText editTexBread;
    private EditText editTexNote;
    Spinner spinnerEating, insulin_length;
    RadioGroup radioGroupWellbeing;

    String[] data = {"one", "two", "three", "four", "five"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edite_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            Intent intent = new Intent(NewNoteActivity.this, HomeActivity.class);
            startActivity(intent);

        }

        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);


        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
        toolbar.setTitleTextAppearance(this, R.style.TextAppearance);

        Intent intent = getIntent();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        radioGroupWellbeing = (RadioGroup) findViewById(R.id.radioGroup);
        spinnerEating = (Spinner) findViewById(R.id.spinnerEat);
        insulin_length = (Spinner) findViewById(R.id.spinner);
        editTextSugar = findViewById(R.id.textSugar);
        editTextData = findViewById(R.id.dataText);
        editTextTime = findViewById(R.id.timeText);
        editTexIns = findViewById(R.id.textInsulin);
        editTexWeight = findViewById(R.id.editText11);
        editTexBread = findViewById(R.id.editText12);
        editTexNote = findViewById(R.id.noteText);


        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Редактирование записи");
            editTextSugar.setText(intent.getStringExtra(EXTRA_SUGAR));
            editTextData.setText(intent.getStringExtra(EXTRA_DATA));

        } else {
            setTitle("Новая запись");
        }

        final List<String> items = new ArrayList<>();
        for (int i = 0; i < 700; i++) {
            sugar_number = sugar_number + 0.1;
            items.add(String.format("%.1f", sugar_number));
        }

        editTextData.setText(dateText);
        editTextTime.setText(timeText);
        wheelView = findViewById(R.id.wheelView);
        wheelView.setItems(items);
        textSugar = findViewById(R.id.textSugar);
        textgiper = findViewById(R.id.giper_or_gipo);

        wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectedListener() {
            @Override
            public void onWheelItemSelected(WheelView wheelView, int position) {
                textSugar.setText(String.format("%1$s", wheelView.getItems().get(position)));
                String sugarValue = textSugar.getText().toString();
                sugarValue = sugarValue.replace(',', '.');
                double sugar = Double.parseDouble(sugarValue);
                if (sugar > 7) {
                    textgiper.setTextSize(14);
                    textgiper.setText(R.string.top_sugar);
                } else if (sugar < 3.3) {
                    textgiper.setTextSize(14);
                    textgiper.setText(R.string.down_sugar);
                } else {
                    textgiper.setTextSize(0);
                    textgiper.setText("");
                }
            }

            @Override
            public void onWheelItemChanged(WheelView wheelView, int position) {
                //wheelView.setText(String.format("onWheelItemChanged：%1$s", wheelView.getItems().get(position)));
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<Insuline> insList = new ArrayList<>();

        insList.add(new Insuline("короткий"));
        insList.add(new Insuline("длинный"));

        ArrayList<Eating> eatList = new ArrayList<>();

        eatList.add(new Eating("натощак"));
        eatList.add(new Eating("до еды"));
        eatList.add(new Eating("после еды"));

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Spinner spinnerEat = (Spinner) findViewById(R.id.spinnerEat);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, insList);

        spinner.setAdapter(spinnerAdapter);
        spinnerEat.setOnItemSelectedListener(this);

        SpinnerAdapterEating spinnerAdapterEating = new SpinnerAdapterEating(this, eatList);

        spinnerEat.setAdapter(spinnerAdapterEating);

        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(NewNoteActivity.this.getSupportFragmentManager(), "time picker");
            }

        });
        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(NewNoteActivity.this.getSupportFragmentManager(), "date picker");
            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        eats = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void saveNote() {
        String sugar_level = textSugar.getText().toString();
        String description = editTexNote.getText().toString();
        String date = editTextData.getText().toString();
        String time = editTextTime.getText().toString();
        int eat = spinnerEating.getSelectedItemPosition();
        String eating = "натощак";
        String insulin_length = spinnerEating.getSelectedItem().toString();
        String injected_insulin = editTexIns.getText().toString();
        String weight = editTexWeight.getText().toString();
        String bread_units = editTexBread.getText().toString();
        String wellbeing = "Нормальное";
        sugar_level = sugar_level.replace(',', '.');
        int id_wellbeing = radioGroupWellbeing.getCheckedRadioButtonId();

        System.out.println(id_wellbeing);
        if (sugar_level.trim().isEmpty()) {
            Toast.makeText(this, "Введите уровень сахара", Toast.LENGTH_SHORT).show();
            return;
        }

        if (id_wellbeing == 2131296424) {
            wellbeing = "Плохое";
        }
        if (id_wellbeing == 2131296426) {
            wellbeing = "Нормальное";
        }
        if (id_wellbeing == 2131296425) {
            wellbeing = "Хорошее";
        }
        if (eat == 0) {
            eating = "натощак";
        }
        if (eat == 1) {
            eating = "до еды";
        }
        if (eat == 2) {
            eating = "после еды";
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_SUGAR, sugar_level);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_DATA, date);
        data.putExtra(EXTRA_TIME, time);
        data.putExtra(EXTRA_WELLBEING, wellbeing);
        data.putExtra(EXTRA_EATING, eating);
        data.putExtra(EXTRA_INSULINLEN, insulin_length);
        data.putExtra(EXTRA_INJECETED, injected_insulin);
        data.putExtra(EXTRA_WEIGHT, weight);
        data.putExtra(EXTRA_BREAD, bread_units);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

}
