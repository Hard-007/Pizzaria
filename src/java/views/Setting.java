package src.java.views;

import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

import java.net.*;
import java.net.URL.*;

import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.resources.config.DBConnection;

public class Setting extends JPanel{
    static Dimension buttonDimension = new Dimension(100, 50);
    static Dimension scrollPaneDimension = new Dimension(1200, 644);
    static Dimension paneDimension = new Dimension(830, 4000);
    static Dimension frameDimension = new Dimension(1344, 748);

    public static Dimension getButtonDimension(){
        return buttonDimension;
    }
    public static Dimension getScrollPaneDimension(){
        return scrollPaneDimension;
    }
    public static Dimension getPaneDimension(){
        return paneDimension;
    }
    public static Dimension getFrameDimension(){
        return frameDimension;
    }


    public Setting(){
        JPanel Setting = new JPanel(new BorderLayout());
		Label settingText = new Label("Definicoes");
		Setting.add(settingText, BorderLayout.NORTH);

        add(Setting);
    }
}
