package io.github.freeman0211.library.apl;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by freeman on 16/2/17.
 */
public class AutoPhotoLayout extends ViewGroup {

    /**
     * the space of item
     */
    private int mItemSpace = 0;

    private static final int DEFAULT_ITEM_COUNT_MAX = 9;
    private static final int DEFAULT_COLUMN = 3;

    private int mColumns = DEFAULT_COLUMN;

    private int mMaxCount = DEFAULT_ITEM_COUNT_MAX;



    public AutoPhotoLayout(Context context) {
        super(context);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs (AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AutoPhotoLayout);

        if (ta.hasValue(R.styleable.AutoPhotoLayout_apl_itemSpace)) {
            int divideResId = ta.getResourceId(R.styleable.AutoPhotoLayout_apl_itemSpace, 0);
            if (divideResId != 0) {
                mItemSpace = getResources().getDimensionPixelSize(divideResId);
            }
        }

        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int childCount = getChildCount();
        View child = null;
        MarginLayoutParams childLp;

        if (childCount == 1) {
            child = getChildAt(0);
            childLp = (MarginLayoutParams) child.getLayoutParams();
            width = child.getMeasuredWidth() + childLp.leftMargin + childLp.rightMargin;
            height = child.getMeasuredHeight() + childLp.topMargin + childLp.bottomMargin;
            if (heightMode != MeasureSpec.EXACTLY) {
                heightSize = height + getPaddingTop() + getPaddingBottom();
            }
        } else {
            final int paddingHorizontal = getPaddingLeft() + getPaddingRight();
            int itemWidth = (widthSize - paddingHorizontal - mItemSpace * (mColumns + 1)) / mColumns;
            int cHeight = 0;
            for (int i = 0; i < childCount & i < mMaxCount; i++) {
                child = getChildAt(i);
                childLp = (MarginLayoutParams) child.getLayoutParams();
                final int marginHorizontal = childLp.leftMargin + childLp.rightMargin;
                final int marginVertical = childLp.topMargin + childLp.bottomMargin;

                child.measure(MeasureSpec.makeMeasureSpec(itemWidth - marginHorizontal, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(itemWidth - marginVertical, MeasureSpec.EXACTLY));

                cHeight = child.getMeasuredHeight();
            }

            heightMode = MeasureSpec.EXACTLY;

            if (childCount % mColumns == 0) {
                height = cHeight * (childCount / mColumns) + mItemSpace * ((childCount / mColumns) + 1);
            } else if (childCount % 2 == 0 && childCount <= 4) {
                height = cHeight * (childCount / 2) + mItemSpace * ((childCount / mColumns) + 1);
            } else {
                height = cHeight * (childCount / mColumns + 1) + mItemSpace * ((childCount / mColumns + 1) + 1);
            }
            width = widthSize;

        }
        height += getPaddingTop() + getPaddingBottom();

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? width : widthSize,
                (heightMode == MeasureSpec.EXACTLY) ? height : heightSize);
    }

    @Override
    protected final void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        View child = null;
        final int originalLeft = getPaddingLeft() + mItemSpace / 2;
        int cLeft = originalLeft;
        int cTop = getPaddingTop() + mItemSpace / 2;
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams childLp = null;

        if (childCount == 1) {
            child = getChildAt(0);
            childLp = (MarginLayoutParams) child.getLayoutParams();
            cWidth = child.getMeasuredWidth();
            cHeight = child.getMeasuredHeight();
            child.layout(cLeft + childLp.leftMargin, cTop + childLp.topMargin,
                    cLeft + childLp.leftMargin + cWidth, cTop + childLp.topMargin + cHeight);
        } else if (childCount % 2 == 0 && childCount <= 4) {
            for (int i = 0; i < childCount & i < mMaxCount; i++) {
                child = getChildAt(i);
                childLp = (MarginLayoutParams) child.getLayoutParams();
                cWidth = child.getMeasuredWidth();
                cHeight = child.getMeasuredHeight();
                if (i != 0 && i % 2 == 0) {
                    cLeft = originalLeft;
                    cTop += cHeight + mItemSpace;
                }
                child.layout(cLeft + childLp.leftMargin + mItemSpace / 2, cTop + childLp.topMargin + mItemSpace / 2,
                        cLeft + childLp.leftMargin + cWidth, cTop + childLp.topMargin + cHeight);
                cLeft += cWidth + childLp.leftMargin + childLp.rightMargin + mItemSpace;
            }
        } else {
            for (int i = 0; i < childCount & i < mMaxCount; i++) {
                child = getChildAt(i);
                childLp = (MarginLayoutParams) child.getLayoutParams();
                cWidth = child.getMeasuredWidth();
                cHeight = child.getMeasuredHeight();
                if (i != 0 && i % mColumns == 0) {
                    cLeft = originalLeft;
                    cTop += cHeight + mItemSpace;
                }
                child.layout(cLeft + childLp.leftMargin + mItemSpace / 2, cTop + childLp.topMargin + mItemSpace / 2,
                        cLeft + childLp.leftMargin + cWidth, cTop + childLp.topMargin + cHeight);
                cLeft += cWidth + childLp.leftMargin + childLp.rightMargin + mItemSpace;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /**
     * set the space of child
     * @param itemSpace space
     */
    public void setItemSpace(int itemSpace) {
        mItemSpace = itemSpace;
        requestLayout();
    }


    public interface OnItemClickListener {
        void onItemClick (View view, int childIndex);
    }
}
