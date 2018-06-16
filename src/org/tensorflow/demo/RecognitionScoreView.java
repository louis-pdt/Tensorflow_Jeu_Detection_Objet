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
  private List<Integer> mesObjetsDetecte = new ArrayList<>();
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
      this.results = new ArrayList<>();
      for (Classifier.Recognition result : results){
          for(int i = 0 ; i < mesObjets.size(); i++) {
              if (this.mesObjetsDetecte != null){
                  if (result.getTitle().equals(mesObjets.get(i))) {
                      this.results.add(result);
                      mesObjetsDetecte.set(i, 0);
                  }
              }
          }
      }
      this.results = results;
      postInvalidate();
  }

  public void setMesObjets(List<String> mesObjets){
    this.mesObjets = mesObjets;
    for (String s : mesObjets){
      mesObjetsDetecte.add(1);
    }
  }
  public void setMesObjets(){
      this.mesObjets = new ArrayList<>();
      this.mesObjetsDetecte = new ArrayList<>();
      this.mesObjets.add("bottle");
      this.mesObjetsDetecte.add(1);
      this.mesObjets.add("laptop");
      this.mesObjetsDetecte.add(1);
      this.mesObjets.add("person");
      this.mesObjetsDetecte.add(1);
      this.mesObjets.add("chair");
      this.mesObjetsDetecte.add(1);
  }

  public String ChoixConseil( int S, int C){
      String choix;
      switch(S) {

          case 3:
          case 4:
          case 5:
          case 6:
          case 7:
          case 8:

              choix="T";

              break;

          case 9:

              if(C==2 || C>6){
                  choix="T";
              }
              else{
                  choix="D";
              }

              break;

          case 10:

              if(C>9){
                  choix="T";
              }
              else{
                  choix="D";
              }

              break;

          case 11:

              choix="D";

              break;

          case 12:

              if(C<4 || C>6){
                  choix="T";
              }
              else{
                  choix="S";
              }

              break;

          case 13:
          case 14:
          case 15:
          case 16:

              if(C>6){
                  choix="T";
              }
              else{
                  choix="S";
              }

              break;

          case 17:
          case 18:
          case 19:
          case 20:

              choix="S";

              break;

          default:

              System.out.println("Erreur : La somme de la main du joueur vaut 0 ou plus de 20 ou bien la carte du croupier n'est pas valide.");
              return "ERROR";

      }
      return choix;
  }

  public List<Integer> getObjetsDetecte(){
      return this.mesObjetsDetecte;
  }
  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 10;
    int y = (int) (fgPaint.getTextSize() * 1.5f);

    canvas.drawPaint(bgPaint);


    if (mesObjetsDetecte.size() == 4 && mesObjets.size() ==4) {
      for (int i = 0; i < 3; i+=2) {
        if (mesObjetsDetecte.get(i) == 0 && mesObjetsDetecte.get(i+1)==0) {//true
          canvas.drawText(mesObjets.get(i) + " (ok)   "+ mesObjets.get(i+1) + " (ok)", x, y, fgPaint);
          y += fgPaint.getTextSize() * 1.5f;
        }
        else if (mesObjetsDetecte.get(i) == 0 && mesObjetsDetecte.get(i+1)==1) {//true
          canvas.drawText(mesObjets.get(i) + " (ok)   "+ mesObjets.get(i+1), x, y, fgPaint);
          y += fgPaint.getTextSize() * 1.5f;
        }
        else if (mesObjetsDetecte.get(i) == 1 && mesObjetsDetecte.get(i+1)== 0) {//true
          canvas.drawText(mesObjets.get(i) + "   "+ mesObjets.get(i+1) + " (ok)", x, y, fgPaint);
          y += fgPaint.getTextSize() * 1.5f;
        }
        else{  canvas.drawText(mesObjets.get(i) + "   "+ mesObjets.get(i+1) , x, y, fgPaint);
          y += fgPaint.getTextSize() * 1.5f;
        }
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
