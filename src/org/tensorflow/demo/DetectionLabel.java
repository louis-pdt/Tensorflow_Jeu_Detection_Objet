package org.tensorflow.demo;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Trace;

import org.tensorflow.Graph;
import org.tensorflow.Operation;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;
import org.tensorflow.demo.env.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

public class DetectionLabel {
  private String[] LabelsSearched = new String[4];
  private boolean[] LabelsDetected = new boolean[4];

  public DetectionLabel(String[] labelsSearched) {

    LabelsSearched = labelsSearched;

  }

  public DetectionLabel(boolean[] labelsDetected) {
    LabelsDetected = labelsDetected;
  }

  public void setLabelsDetected(boolean[] labelsDetected) {

    LabelsDetected = labelsDetected;
  }

  public String[] getLabelsSearched() {

    return LabelsSearched;
  }

  public void setLabelsSearched(String[] labelsSearched) {
    LabelsSearched = labelsSearched;
  }

  public boolean[] getLabelsDetected() {
    return LabelsDetected;
  }

  public DetectionLabel(boolean[] labelsDetected, String[] labelsSearched) {
    LabelsDetected = labelsDetected;
    LabelsSearched = labelsSearched;
  }
}
