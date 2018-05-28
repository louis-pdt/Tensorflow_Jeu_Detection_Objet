/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import org.tensorflow.demo.Classifier.Recognition;

import java.util.ArrayList;
import java.util.List;

public class RecognitionScoreView extends View implements ResultsView {
  private static final float TEXT_SIZE_DIP = 24;
  private List<Recognition> results;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint bgPaint;

  private List<String> mesObjets = new ArrayList<>();
  /*mesObjets.add("bottle");
  mesObjets.add("laptop");
  mesObjets.add("person");
  mesObjets.add("chair");*/

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setTextSize(textSizePx);

    bgPaint = new Paint();
    bgPaint.setColor(0xcc4285f4);
  }

  @Override
  public void setResults(final List<Recognition> results) {
    for (Classifier.Recognition result : results){
      for(int i = 0 ; i < mesObjets.size(); i++) {
        if (result.getTitle().equals(mesObjets.get(i))) {
          this.results.add(result);
          mesObjets.remove(i);
        }
      }
    }
    this.results = results;
    postInvalidate();
  }

  public void setMesObjets(List<String> mesObjets){
    this.mesObjets = mesObjets;
  }
  public void setMesObjets(){
    this.mesObjets.add("bottle");
    this.mesObjets.add("laptop");
    this.mesObjets.add("person");
    this.mesObjets.add("chair");
  }

  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 10;
    int y = (int) (fgPaint.getTextSize() * 1.5f);

    canvas.drawPaint(bgPaint);

    if (mesObjets != null) {
      for (String str : mesObjets) {
        canvas.drawText(str, x, y, fgPaint);
        y += fgPaint.getTextSize() * 1.5f;
      }
    }
/*
    if (results != null) {
      for (final Recognition recog : results) {
        canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
        y += fgPaint.getTextSize() * 1.5f;
      }
    }*/
  }
}
