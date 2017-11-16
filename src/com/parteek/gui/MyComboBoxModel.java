package com.parteek.gui;

import javax.swing.DefaultComboBoxModel;

import com.parteek.jebra.methods.MethodStructure;

class MyComboBoxModel extends DefaultComboBoxModel<MethodStructure> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyComboBoxModel(MethodStructure[] items) {
        super(items);
    }
 
    @Override
    public MethodStructure getSelectedItem() {
    	MethodStructure selectedJob = (MethodStructure) super.getSelectedItem();
 
        // do something with this job before returning...
 
        return selectedJob;
    }
}
