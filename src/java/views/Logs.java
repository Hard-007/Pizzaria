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

public class Logs extends JPanel{
    public Logs(){
        JPanel Logs = new JPanel(new BorderLayout());
		Label logText = new Label("Historico de uso do sistema");
		Logs.add(logText, BorderLayout.NORTH);
    }
}
