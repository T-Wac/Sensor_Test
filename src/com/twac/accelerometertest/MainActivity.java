package com.twac.accelerometertest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private TextView mTextView1, mTextView2, mTextView3;
	private TextView mTextView_mag1, mTextView_mag2, mTextView_mag3;
	private TextView mTextView_temp1;
	private TextView mTextView_light1;
	private TextView mTextView_pressure1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView1 = (TextView) findViewById(R.id.text_show1);
		mTextView2 = (TextView) findViewById(R.id.text_show2);
		mTextView3 = (TextView) findViewById(R.id.text_show3);
		mTextView_mag1 = (TextView) findViewById(R.id.text_mag1);
		mTextView_mag2 = (TextView) findViewById(R.id.text_mag2);
		mTextView_mag3 = (TextView) findViewById(R.id.text_mag3);
		mTextView_temp1 = (TextView) findViewById(R.id.text_temp1);
		mTextView_light1 = (TextView) findViewById(R.id.text_light1);
		mTextView_pressure1 = (TextView) findViewById(R.id.text_pressure1);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {

		super.onResume();
		// ע�᷽�򴫸���
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
		// ע��ų�������
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_GAME);

		// ע���¶ȴ�����
		mSensorManager.registerListener(this, mSensorManager
				.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
				SensorManager.SENSOR_DELAY_GAME);
		// ע���ǿ������
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
				SensorManager.SENSOR_DELAY_GAME);
		// ע��ѹ��������
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		// ��ȡ����event�Ĵ���������
		int sensorType = event.sensor.getType();
		StringBuilder sb = null;

		switch (sensorType) {
		case Sensor.TYPE_ORIENTATION:
			StringBuilder sb1 = new StringBuilder();
			sb1.append("��Z��ת���ĽǶȣ�   ");
			sb1.append(values[0]);
			StringBuilder sb2 = new StringBuilder();
			sb2.append("��X��ת���ĽǶȣ�   ");
			sb2.append(values[1]);
			StringBuilder sb3 = new StringBuilder();
			sb3.append("��Y��ת���ĽǶȣ�   ");
			sb3.append(values[2]);
			mTextView1.setText(sb1.toString());
			mTextView2.setText(sb2.toString());
			mTextView3.setText(sb3.toString());
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			StringBuilder sb4 = new StringBuilder();
			sb4.append("X�����ϵĽǶȣ�   ");
			sb4.append(values[0]);
			StringBuilder sb5 = new StringBuilder();
			sb5.append("Y�����ϵĽǶȣ�   ");
			sb5.append(values[1]);
			StringBuilder sb6 = new StringBuilder();
			sb6.append("Z�����ϵĽǶȣ�   ");
			sb6.append(values[2]);
			mTextView_mag1.setText(sb4.toString());
			mTextView_mag2.setText(sb5.toString());
			mTextView_mag3.setText(sb6.toString());
			break;

		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			sb = new StringBuilder();
			sb.append("��ǰ�¶�Ϊ��  ");
			sb.append(values[0]);
			mTextView_temp1.setText(sb.toString());
			break;
		case Sensor.TYPE_LIGHT:
			sb = new StringBuilder();
			sb.append("��ǰ��ǿΪ��  ");
			sb.append(values[0]);
			mTextView_light1.setText(sb.toString());
			break;
		case Sensor.TYPE_PRESSURE:
			sb = new StringBuilder();
			sb.append("��ǰѹǿΪ��  ");
			sb.append(values[0]);
			mTextView_pressure1.setText(sb.toString());
			break;
		default:
			break;
		}

	}
}
