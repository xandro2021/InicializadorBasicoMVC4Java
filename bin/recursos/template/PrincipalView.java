package com.syncronizarworkspace.mvc.views;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PrincipalView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();

		setContentPane(contentPane);
	}

	public void init() {
		setLocationRelativeTo(null);
		setTitle("Titulo");
		setVisible(true);
	}

}
