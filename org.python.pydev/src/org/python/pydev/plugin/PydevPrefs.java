/*
 * Author: atotic
 * Created: Jun 23, 2003
 * License: Common Public License v1.0
 */
package org.python.pydev.plugin;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.python.pydev.ui.InterpreterEditor;

/**
 * Pydev preference page.
 * 
 * <p>Uses FieldEditor framework for preference editing.
 * <p>Defaults are declared here as constants.
 * <p>There is a string constant for every prefernce you can use for access
 * <p>Framework takes care of storing of the prefs
 * <p>The meaning of preferences are documented in user docs, for details grep
 * the source for the particular string.
 */
public class PydevPrefs extends FieldEditorPreferencePage 
	implements IWorkbenchPreferencePage{

	// Preferences	
	public static final String SUBSTITUTE_TABS = "SUBSTITUTE_TABS";
	public static final boolean DEFAULT_SUBSTITUTE_TABS = true;
	public static final String GUESS_TAB_SUBSTITUTION = "GUESS_TAB_SUBSTITUTION";
	public static final boolean DEFAULT_GUESS_TAB_SUBSTITUTION = true;
	public static final String TAB_WIDTH = "TAB_WIDTH";
	public static final int DEFAULT_TAB_WIDTH = 4;
	public static final String CODE_COLOR = "CODE_COLOR";
	private static final RGB DEFAULT_CODE_COLOR = new RGB(0, 0, 0);
	public static final String KEYWORD_COLOR = "KEYWORD_COLOR";
	private static final RGB DEFAULT_KEYWORD_COLOR = new RGB(160, 32, 240);
	public static final String STRING_COLOR = "STRING_COLOR";
	private static final RGB DEFAULT_STRING_COLOR = new RGB(120, 130, 61);
	public static final String COMMENT_COLOR = "COMMENT_COLOR";
	private static final RGB DEFAULT_COMMENT_COLOR = new RGB(178, 34, 34);
	public static final String INTERPRETER_PATH = "INTERPRETER_PATH";
	protected static final String DEFAULT_INTERPRETER_PATH = "python";
	public static final String HYPERLINK_COLOR = "HYPERLINK_COLOR";
	private static final RGB DEFAULT_HYPERLINK_COLOR = new RGB(0, 0, 238);
	
	/**
	 * Initializer sets the preference store
	 */
	public PydevPrefs() {
		super(GRID);
		setPreferenceStore(PydevPlugin.getDefault().getPreferenceStore());
	}

	static public Preferences getPreferences() {
		return 	PydevPlugin.getDefault().getPluginPreferences();
	}
	
	public static String[] getInterpreters() {
		String interpreters = getPreferences().getString(PydevPrefs.INTERPRETER_PATH);
		return InterpreterEditor.getInterpreterList(interpreters);
	}

	public void init(IWorkbench workbench) {		
	}
	
	/**
	 * Creates the editors
	 */
	protected void createFieldEditors() {
		Composite p = getFieldEditorParent();
		addField(new BooleanFieldEditor(
			SUBSTITUTE_TABS, "Substitute spaces for tabs?", p));
		addField(new BooleanFieldEditor(
				GUESS_TAB_SUBSTITUTION, "Assume tab spacing when files contain tabs?", p));
		IntegerFieldEditor ife = new IntegerFieldEditor(TAB_WIDTH, "Tab length", p, 1);
		ife.setValidRange(1, 8);
		// you can't restrict widget width on IntegerFieldEditor for now
		addField(ife);
		addField(new ColorFieldEditor(
			CODE_COLOR, "Code", p));
		addField(new ColorFieldEditor(
			KEYWORD_COLOR, "Keywords", p));
		addField(new ColorFieldEditor(
			STRING_COLOR, "Strings", p));
		addField(new ColorFieldEditor(
			COMMENT_COLOR, "Comments", p));
	}
	
	/**
	 * Sets default preference values
	 */
	protected static void initializeDefaultPreferences(Preferences prefs) {
		prefs.setDefault(SUBSTITUTE_TABS, DEFAULT_SUBSTITUTE_TABS);
		prefs.setDefault(GUESS_TAB_SUBSTITUTION, DEFAULT_GUESS_TAB_SUBSTITUTION);
		prefs.setDefault(TAB_WIDTH, DEFAULT_TAB_WIDTH);
		prefs.setDefault(CODE_COLOR,StringConverter.asString(DEFAULT_CODE_COLOR));
		prefs.setDefault(KEYWORD_COLOR,StringConverter.asString(DEFAULT_KEYWORD_COLOR));
		prefs.setDefault(STRING_COLOR,StringConverter.asString(DEFAULT_STRING_COLOR));
		prefs.setDefault(COMMENT_COLOR,StringConverter.asString(DEFAULT_COMMENT_COLOR));
		prefs.setDefault(HYPERLINK_COLOR, StringConverter.asString(DEFAULT_HYPERLINK_COLOR));
	}
}
