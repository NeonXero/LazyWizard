package net.neonlotus.lazywizard.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Stankus on 10/12/2014.
 */
public class ResourceView extends RelativeLayout {
    private View mValue;
    private ImageView mImage;

    public ResourceView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
