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
    static int buttonWidth = 50;
    static int scrollPaneWidth = 995;
    static int scrollPaneHeight = 600;
    static int paneX = 830;
    static int paneY = 4000;

    public static int getButtonWidth(){
        return buttonWidth;
    }
    public static int getScrollPaneWidth(){
        return scrollPaneWidth;
    }
    public static int getScrollPaneHeight(){
        return scrollPaneHeight;
    }
    public static int getPaneX(){
        return paneX;
    }
    public static int getPaneY(){
        return paneY;
    }


    public Setting(){
        JPanel Setting = new JPanel(new BorderLayout());
		Label settingText = new Label("Definicoes");
		Setting.add(settingText, BorderLayout.NORTH);

        add(Setting);
    }
}
