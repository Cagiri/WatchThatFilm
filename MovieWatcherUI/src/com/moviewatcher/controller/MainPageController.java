package com.moviewatcher.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.moviewatcher.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainPageController extends VBox {

	Main main;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnSettings;

	@FXML
	private GridPane gpMovies;

	@FXML
	void initialize() {

		btnSettings.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> addRowToTable());
	}

	public void addRowToTable() {

		String str = "a";
		System.out.println(str);
		
		Label lbl = new Label("FIRST LABEL TEST");
		Label lbl2 = new Label("FIRST LABEL TEST");
		Label lbl3 = new Label("FIRST LABEL TEST");
		Label lbl4 = new Label("FIRST LABEL TEST");
		int rowSize = gpMovies.getRowConstraints().size();
		gpMovies.addRow(rowSize, lbl,lbl2,lbl3,lbl4);

	}
}