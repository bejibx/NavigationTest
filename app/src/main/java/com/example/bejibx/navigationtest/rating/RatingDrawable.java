package com.example.bejibx.navigationtest.rating;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RatingDrawable extends Drawable {

    @NonNull
    private final Rect drawRect = new Rect();
    @NonNull
    private final RectF drawRectF = new RectF();
    @NonNull
    private final Paint maskingPaint = new Paint();

    @NonNull
    private final Drawable maskDrawable;
    private final int offsetPx;
    private final int count;
    private final int colorNormal;
    private final int colorHighlight;

    private int starWidth;
    private int starHeight;
    private int highlightedCount;

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    private RatingDrawable(
            @NonNull final Drawable maskDrawable,
            final int offsetPx,
            final int colorNormal,
            final int colorHighlight,
            final int count) {

        this.maskDrawable = maskDrawable.mutate();
        this.offsetPx = offsetPx;
        this.count = count;
        this.colorNormal = colorNormal;
        this.colorHighlight = colorHighlight;

        starWidth = maskDrawable.getIntrinsicWidth();
        starHeight = maskDrawable.getIntrinsicHeight();

        maskingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        drawRect.set(0, 0, starWidth, starHeight);
        for (int i = 0; i < count; i++) {
            drawRectF.set(drawRect);
            final int restoreToCount = canvas.saveLayer(drawRectF, null);
            canvas.drawColor(i < highlightedCount ? colorHighlight : colorNormal);
            canvas.saveLayer(drawRectF, maskingPaint);
            maskDrawable.setBounds(drawRect);
            maskDrawable.draw(canvas);
            canvas.restoreToCount(restoreToCount);
            drawRect.offset(starWidth + offsetPx, 0);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        // no-op
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        // no-op
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public int getIntrinsicWidth() {
        return maskDrawable.getIntrinsicWidth() * count + offsetPx * (count - 1);
    }

    @Override
    public int getIntrinsicHeight() {
        return maskDrawable.getIntrinsicHeight();
    }

    public void setHighlightedCount(final int value) {
        //todo check compare to count
        //todo check is non-negative
        highlightedCount = value;
        invalidateSelf();
    }

    public static class Builder {

        @Nullable
        private Drawable maskDrawable;
        @Nullable
        private Integer offsetPx;
        @Nullable
        private Integer colorNormal;
        @Nullable
        private Integer colorHighlight;
        @Nullable
        private Integer count;

        private Builder() {

        }

        @NonNull
        public Builder mask(@NonNull final Drawable drawable) {
            //TODO check for null
            //TODO check for intrinsic size
            maskDrawable = drawable;
            return this;
        }

        @NonNull
        public Builder offset(final int offset) {
            //TODO add dimension unit
            offsetPx = offset;
            return this;
        }

        @NonNull
        public Builder colorNormal(@ColorInt final int color) {
            colorNormal = color;
            return this;
        }

        @NonNull
        public Builder colorHighlight(@ColorInt final int color) {
            colorHighlight = color;
            return this;
        }

        @NonNull
        public Builder count(final int count) {
            this.count = count;
            return this;
        }

        @NonNull
        public RatingDrawable build() {
            //TODO check fields initialized
            //TODO check count
            //TODO check offset
            //noinspection ConstantConditions
            return new RatingDrawable(maskDrawable, offsetPx, colorNormal, colorHighlight, count);
        }
    }
}
