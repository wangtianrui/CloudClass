package com.scorpiomiku.cloudclass.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ScorpioMiku on 2019/7/4.
 */

public class ChartMaker {
    /**
     * 创建一条折线
     *
     * @param linename
     * @return
     */
    public static void initSingleLineChart(LineChart mLineChart, ArrayList<Entry> pointValues, String linename) {

        mLineChart.setNoDataText("没有数据喔~~");
        //设置是否绘制chart边框的线
        mLineChart.setDrawBorders(true);
        //设置chart边框线颜色
        mLineChart.setBorderColor(Color.GRAY);
        //设置chart边框线宽度
        mLineChart.setBorderWidth(1f);
        //设置chart是否可以触摸
        mLineChart.setTouchEnabled(true);
        //设置是否可以拖拽
        mLineChart.setDragEnabled(true);
        //设置是否可以缩放 x和y，默认true
        mLineChart.setScaleEnabled(false);
        //设置是否可以通过双击屏幕放大图表。默认是true
        mLineChart.setDoubleTapToZoomEnabled(false);
        //设置chart动画
        mLineChart.animateXY(1000, 1000);

        //=========================设置图例=========================
        // 像"□ xxx"就是图例
        Legend legend = mLineChart.getLegend();
        legend.setEnabled(false);


        //=======================设置X轴显示效果==================
        XAxis xAxis = mLineChart.getXAxis();
        //是否启用X轴
        xAxis.setEnabled(true);
        //是否绘制X轴线
        xAxis.setDrawAxisLine(true);
        //设置X轴上每个竖线是否显示
        xAxis.setDrawGridLines(true);
        //设置是否绘制X轴上的对应值(标签)
        xAxis.setDrawLabels(true);
        //设置X轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置竖线为虚线样式
        // xAxis.enableGridDashedLine(10f, 10f, 0f);
        //设置x轴标签数
        xAxis.setLabelCount(8, false);
        xAxis.setTextSize(5);
        //图表第一个和最后一个label数据不超出左边和右边的Y轴
        // xAxis.setAvoidFirstLastClipping(true);
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线

        //修改横轴
        //准备好每个点对应的x轴数值
        List<String> list = new ArrayList<>();
        for (int i = 0; i < pointValues.size(); i++) {
//            list.add(String.valueOf(i + 1).concat("号"));
        }
        xAxis.setValueFormatter(new IndexAxisValueFormatter(list));


        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setEnabled(false);
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setEnabled(false);
        leftAxis.setDrawAxisLine(true);
        rightAxis.setAxisMinimum(0);

        //点构成的某条线
        LineDataSet lineDataSet = new LineDataSet(pointValues, "体重");
        //设置该线的颜色
//        lineDataSet.setColor(R.color.color_bar_background);
        //设置每个点的颜色
        lineDataSet.setCircleColor(0xff0171c9);
        //设置该线的宽度
        lineDataSet.setLineWidth(0f);

        //设置每个坐标点的圆大小
        //lineDataSet.setCircleRadius(1f);
        //设置是否画圆
        lineDataSet.setDrawCircles(false);
        // 设置平滑曲线模式
        //  lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //设置线一面部分是否填充颜色
        lineDataSet.setDrawFilled(true);
        //设置填充的颜色
        lineDataSet.setFillColor(0x20A0FF);
        //设置是否显示点的坐标值
        lineDataSet.setDrawValues(false);


        //隐藏x轴描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);
        mLineChart.setDrawBorders(false);
        //线的集合（可单条或多条线）
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        //把要画的所有线(线的集合)添加到LineData里
        LineData lineData = new LineData(dataSets);
        //把最终的数据setData
        mLineChart.setData(lineData);
        mLineChart.invalidate();

    }

}
