package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class FlipBoxView extends View {
	
	private int nToggleState = 0;
	private int[] colors = {Color.WHITE, Color.RED, Color.BLACK};
	
	public FlipBoxView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		Paint p = new Paint();
		p.setColor(colors[nToggleState % colors.length]);
		
		Log.d("hcx","w=" + this.getWidth() + ",h=" + this.getHeight());
		canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), p);
		
		return;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() ==  MotionEvent.ACTION_HOVER_ENTER
			|| event.getAction() ==  MotionEvent.ACTION_HOVER_EXIT)
		{
			nToggleState++;
		}
		this.invalidate();
		return true;
	}

	
}
