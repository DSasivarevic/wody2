package wody.wody;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

import org.w3c.dom.Attr;

/**
 * Created by Falch on 18/11/15.
 */
public class RoundButton extends Button {
    Paint paint = new Paint();
    int col = 0xFF64DD17;

    public RoundButton(Context context) {
        super(context);
        paint.setColor(col);
    }

    public RoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(col);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setColor(col);
    }

    public void setRoundColor(int color){
        paint.setColor(color);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(this.getWidth()/2,this.getWidth()/2,this.getWidth()/2,paint);
        super.onDraw(canvas);
    }
}
