import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class RendererView {

	// TODO : REMOVE IF UNNECESSARRY
	private Modeler model;
	
	// The main container of the view
	private HBox container;
	// The box where the drawing happens
	private VBox displayBox;
	// The control-panel
	private VBox controlBox;
	
	private Label instructionLabel;
	private Button connectButton;
	private Button closeConnectionButton;
	private HBox getDataButtonWrapper;
	private Button fetchButton;
	private Button streamButton;
	private TextArea logs;
	
	public RendererView(Modeler model) {
		this.model = model;
		
		container = new HBox(2);
		container.setAlignment(Pos.TOP_RIGHT);
		
		displayBox = new VBox();
		//TODO: settings for the display (if any)
		initDisplayBox();
		
		controlBox = new VBox(4);
		initControlBox();
		
		container.getChildren().addAll(displayBox, controlBox);
	}
	
	
	private void initDisplayBox() {
		displayBox.setAlignment(Pos.CENTER);
		displayBox.setMinWidth(400);
		displayBox.setSpacing(10);
		displayBox.setPadding(new Insets(10, 10, 10, 10));
	}
	
	private void initControlBox() {
		controlBox.setAlignment(Pos.CENTER);
		controlBox.setMaxWidth(350);
		controlBox.setSpacing(10);
		controlBox.setPadding(new Insets(10, 10, 10, 10));
		
		instructionLabel = new Label("Click Start to connect with your Arduino");
		instructionLabel.setFont(new Font("", 14));
		
		connectButton = new Button("Start");
		connectButton.setMinWidth(100);
		connectButton.setMinHeight(40);
		connectButton.setFont(new Font("", 20));
		
		closeConnectionButton = new Button("Stop");
		closeConnectionButton.setMinWidth(100);
		closeConnectionButton.setMinHeight(40);
		closeConnectionButton.setFont(new Font("", 20));
		//this button is disabled before a connection is established
		closeConnectionButton.setDisable(true);
		
		HBox connectionButtonWrapper = new HBox(2);
		connectionButtonWrapper.getChildren().addAll(connectButton, closeConnectionButton);
		connectionButtonWrapper.setSpacing(20);
		connectionButtonWrapper.setPadding(new Insets(10, 10, 10, 10));
		connectionButtonWrapper.setAlignment(Pos.CENTER);
		
		fetchButton = new Button("Fetch");
		fetchButton.setMinWidth(100);
		fetchButton.setMinHeight(40);
		fetchButton.setFont(new Font("", 20));
		
		streamButton = new Button("Stream");
		streamButton.setMinWidth(100);
		streamButton.setMinHeight(40);
		streamButton.setFont(new Font("", 20));
		
		getDataButtonWrapper = new HBox(2);
		getDataButtonWrapper.getChildren().addAll(fetchButton, streamButton);
		getDataButtonWrapper.setSpacing(20);
		getDataButtonWrapper.setPadding(new Insets(10, 10, 10, 10));
		getDataButtonWrapper.setAlignment(Pos.CENTER);
		//this box is hidden before a connection is established
		getDataButtonWrapper.setVisible(false);
		
		logs = new TextArea("");
		logs.setEditable(false);
		logs.setMinWidth(300);
		logs.setMinHeight(50);
		logs.setWrapText(true);
		
		controlBox.getChildren().addAll(
				instructionLabel, connectionButtonWrapper, getDataButtonWrapper, logs);
	}
	
	
	public void addConnectionButtonsHandler(EventHandler<ActionEvent> connectButtonHandler, 
			EventHandler<ActionEvent> closeConnectionButtonHandler) {
		
		connectButton.setOnAction(connectButtonHandler);
		closeConnectionButton.setOnAction(closeConnectionButtonHandler);
	}
	
	public void addFetchStreamButtonsHandler(EventHandler<ActionEvent> fetchButtonHandler, 
			EventHandler<ActionEvent> streamButtonHandler) {
		
		fetchButton.setOnAction(fetchButtonHandler);
		streamButton.setOnAction(streamButtonHandler);
	}
	
	public void toggleControlPaneForArduinoConnected(boolean connected) {
		connectButton.setDisable(connected);
		closeConnectionButton.setDisable(!connected);
		getDataButtonWrapper.setVisible(connected);
		if (connected) {
			instructionLabel.setText("Click Stop to close the connection with Arduino");
		} else {
			instructionLabel.setText("Click Start to connect with your Arduino");
		}
	}
	
	public void displayError(String errorMessage) {
		logs.setText(errorMessage);
	}
	
	public Node getNode() {
        return container;
    }
}
