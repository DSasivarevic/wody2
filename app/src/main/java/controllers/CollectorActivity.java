package controllers;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import models.ExercisePrediction;
import models.TimeExercise;
import models.WOD;
import wody.wody.R;

public class CollectorActivity extends Fragment {

	private enum State {
		IDLE, COLLECTING, TRAINING, CLASSIFYING
	}

	;

	private final String[] mLabels = {Globals.CLASS_LABEL_STANDING,
			Globals.CLASS_LABEL_WALKING, Globals.CLASS_LABEL_RUNNING,
			Globals.CLASS_LABEL_OTHER};

	private RadioGroup radioGroup;
	private final RadioButton[] radioBtns = new RadioButton[4];
	private Intent mServiceIntent;
	private File mFeatureFile;

	private TextView txtPrediction;
	private State mState;
	private Button btnDelete;
	private Button btnCollect;
	private WOD wod;

	View rootview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.main, container, false);
		super.onCreate(savedInstanceState);
		radioGroup = (RadioGroup) rootview.findViewById(R.id.radioGroupLabels);
		radioBtns[0] = (RadioButton) rootview.findViewById(R.id.radioStanding);
		radioBtns[1] = (RadioButton) rootview.findViewById(R.id.radioWalking);
		radioBtns[2] = (RadioButton) rootview.findViewById(R.id.radioRunning);
		radioBtns[3] = (RadioButton) rootview.findViewById(R.id.radioOther);

		btnDelete = (Button) rootview.findViewById(R.id.btnDeleteData);
		btnCollect = (Button) rootview.findViewById(R.id.btnCollect);

		mState = State.IDLE;
		mFeatureFile = new File(getActivity().getExternalFilesDir(null),
				Globals.FEATURE_FILE_NAME);
		mServiceIntent = new Intent(getActivity(), SensorsService.class);

		txtPrediction = (TextView) rootview.findViewById(R.id.txtPrediction);
		final Button btn = (Button) rootview.findViewById(R.id.btnStart);
		final Button btn2 = (Button) rootview.findViewById(R.id.button2);
		int checkedId = radioGroup.getCheckedRadioButtonId();
		RadioButton checkedRadioButton = (RadioButton) rootview.findViewById(checkedId);
		String text = checkedRadioButton.getText().toString();

		wod = new WOD("", "", 1);
		wod.addExercise(new TimeExercise(text, 10));
		//btn2.setVisibility(View.GONE);
		btnCollect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int checkedId = radioGroup.getCheckedRadioButtonId();
				RadioButton checkedRadioButton = (RadioButton) rootview.findViewById(checkedId);
				String text = checkedRadioButton.getText().toString();
				wod.start();
				btnCollect.setBackgroundColor(Color.RED);
				//wod.setName(((TextView) rootview.findViewById(R.id.wodName)).getText().toString());
				wod.setName("Test");

				if (btnCollect.getText().equals("Start")) {
					wod.addExercise(new TimeExercise(text, 10));
					TimeExercise ex = (TimeExercise) wod.getExercises().get(wod.getCurrentExercise());
					ex.start();
					//btn.setText(ex.getRepetitions() + " " + ex.getName());
				} else {

					int next = wod.nextExercise();
					Log.e("ERROR", "" + next);
					if (next < wod.getExercises().size()) {
						Log.e("ERROR", "" + wod.getExercises().size());
						TimeExercise old_ex = (TimeExercise) wod.getExercises().get(next);
						old_ex.stop();
						TimeExercise ex = (TimeExercise) wod.getExercises().get(next);
						btnCollect.setText(ex.getRepetitions() + " " + ex.getName());

						int acvitivtyId = radioGroup.indexOfChild(getActivity().findViewById(radioGroup
								.getCheckedRadioButtonId()));
						String label = mLabels[acvitivtyId];

						Bundle extras = new Bundle();
						extras.putString(Globals.CLASS_LABEL_KEY, label);
						mServiceIntent.putExtras(extras);
						getActivity().startService(mServiceIntent);


						ex.start();
					} else {
						TimeExercise old_ex = (TimeExercise) wod.getExercises().get(wod.getExercises().size() - 1);
						old_ex.stop();
						wod.stop();
						btnCollect.setText("DONE");
						//btn2.setVisibility(View.VISIBLE);
						getActivity().stopService(mServiceIntent);
						((NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE)).cancelAll();
						btnCollect.setBackgroundColor(Color.GRAY);

						final Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								ExercisePrediction ex = new ExercisePrediction(getContext(), "fft_all1.model");
								ArrayList<String> torben = ex.getPrediction(getContext(), "/storage/emulated/0/Android/data/wody.wody/files/features.arff", 3);

								txtPrediction.setText("Predictions");
								for (String prediction : torben) {
									txtPrediction.append("    "+prediction+"\n");
								}
							}
						}, 1000);

					}

				}

			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState())) {
					if (mFeatureFile.exists()) {
						mFeatureFile.delete();
					}

					Toast.makeText(getActivity().getApplicationContext(),
							R.string.ui_collector_toast_file_deleted,
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		/*btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Server server = new Server();
				Gson gson = new Gson();
				String s = gson.toJson(wod);
				server.saveWOD(s);
				Toast.makeText(rootview.getContext(), "SAVED TO SERVER", Toast.LENGTH_LONG);
			}
		});*/


		/*btnCollect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mState == State.IDLE) {
					mState = State.COLLECTING;
					((Button) v).setText(R.string.ui_collector_button_stop_title);
					btnDelete.setEnabled(false);
					radioBtns[0].setEnabled(false);
					radioBtns[1].setEnabled(false);
					radioBtns[2].setEnabled(false);
					radioBtns[3].setEnabled(false);

					int acvitivtyId = radioGroup.indexOfChild(getActivity().findViewById(radioGroup
							.getCheckedRadioButtonId()));
					String label = mLabels[acvitivtyId];

					Bundle extras = new Bundle();
					extras.putString(Globals.CLASS_LABEL_KEY, label);
					mServiceIntent.putExtras(extras);

					getActivity().startService(mServiceIntent);

				} else if (mState == State.COLLECTING) {
					mState = State.IDLE;
					((Button) v).setText(R.string.ui_collector_button_start_title);
					btnDelete.setEnabled(true);
					radioBtns[0].setEnabled(true);
					radioBtns[1].setEnabled(true);
					radioBtns[2].setEnabled(true);
					radioBtns[3].setEnabled(true);

					getActivity().stopService(mServiceIntent);
					((NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE)).cancelAll();
				}
			}
		});*/

		return rootview;

	}

	public void onDeleteDataClicked(View view) {

		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			if (mFeatureFile.exists()) {
				mFeatureFile.delete();
			}

			Toast.makeText(getActivity().getApplicationContext(),
					R.string.ui_collector_toast_file_deleted,
					Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onDestroy() {
		// Stop the service and the notification.
		// Need to check whether the mSensorService is null or not.
		if (mState == State.TRAINING) {
			return;
		} else if (mState == State.COLLECTING || mState == State.CLASSIFYING) {
			getActivity().stopService(mServiceIntent);
			((NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE))
					.cancel(Globals.NOTIFICATION_ID);
		}
		getActivity().finish();
		super.onDestroy();
	}

//	public void setPath(String path){
//		ExercisePrediction ex = new ExercisePrediction(getContext(), "disp_model.model");
//		ArrayList<String> torben = ex.getPrediction(getContext(), path, 3);
//
//		txtPrediction.setText("torben er noob");
//		for(String prediction : torben){
//			txtPrediction.append("ssss"+prediction);
//		}

//}

}