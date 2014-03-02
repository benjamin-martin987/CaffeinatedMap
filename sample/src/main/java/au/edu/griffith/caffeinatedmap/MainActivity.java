package au.edu.griffith.caffeinatedmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    static final String NUMBER_OF_CMO_TAG = "numberofcmo";
    static final String CLUSTER_SIZE_TAG = "clustersize";
    static final String CAFFEINATED_CLUSTER_ICON_TAG = "cci";
    static final String TYPE_COUNTING_TAG = "typecounting";
    static final String CMO_VISIBILITY_CHANGE_TAG = "cmovischangeS";

    private TextView mNoCMO, mClusterSize;
    private SeekBar mSBNoCMO, mSBClusterSize;
    private CheckBox mCCI, mTypeCounting, mCMOVisChange;
    private Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mNoCMO = (TextView) findViewById(R.id.tvNoCMO);
        mClusterSize = (TextView) findViewById(R.id.tvClusterSize);
        mSBNoCMO = (SeekBar) findViewById(R.id.sbNoCMO);
        mSBNoCMO.setProgress(Integer.parseInt(mNoCMO.getText().toString()));
        mSBClusterSize = (SeekBar) findViewById(R.id.sbClusterSize);
        mSBClusterSize.setProgress(Integer.parseInt(mClusterSize.getText().toString()));
        mCCI = (CheckBox) findViewById(R.id.cbCCI);
        mTypeCounting = (CheckBox) findViewById(R.id.cbTypeCounting);
        mCMOVisChange = (CheckBox) findViewById(R.id.cbCMOVisChange);
        mStart = (Button) findViewById(R.id.bStart);

        mSBNoCMO.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNoCMO.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSBClusterSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mClusterSize.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mCCI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTypeCounting.setChecked(isChecked);
            }
        });

        mStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bStart:
                Intent intent = new Intent(this, MapActivity.class);
                intent.putExtra(NUMBER_OF_CMO_TAG, mSBNoCMO.getProgress());
                intent.putExtra(CLUSTER_SIZE_TAG, mSBClusterSize.getProgress());
                intent.putExtra(CAFFEINATED_CLUSTER_ICON_TAG, mCCI.isChecked());
                intent.putExtra(TYPE_COUNTING_TAG, mTypeCounting.isChecked());
                intent.putExtra(CMO_VISIBILITY_CHANGE_TAG, mCMOVisChange.isChecked());
                startActivity(intent);
                break;
        }
    }
}
