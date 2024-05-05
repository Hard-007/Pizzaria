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
    public Setting(){
        JPanel Setting = new JPanel(new BorderLayout());
		Label settingText = new Label("Definicoes");
		Setting.add(settingText, BorderLayout.NORTH);
    }
}
