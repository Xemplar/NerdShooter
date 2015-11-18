package com.xemplar.games.launcher.action;

import java.awt.event.MouseEvent;

import com.xemplar.games.launcher.LauncherButton;

public interface OnButtonPress {
	public void onButtonPress(LauncherButton button, MouseEvent e);
}
