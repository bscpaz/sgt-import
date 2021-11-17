package br.com.bscpaz.sgt.vos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HierarchicalDescriptionTest {

	private HierarchicalDescription hierarchicalDescription = new HierarchicalDescription();

	@Test
    public void testJustForwardHierarchicalDescription() {
		int index = -1;
		String assunto = "NOT ALLOWED";
		String codigo = "-1";
		hierarchicalDescription.setValue(index, assunto, codigo);
		assertEquals("", hierarchicalDescription.getValue());

		index = 0;
		assunto = "Run";
		codigo = "10";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10)", hierarchicalDescription.getValue());

		index = 1;
		assunto = "Walk";
		codigo = "20";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Walk (20)", hierarchicalDescription.getValue());

		index = 2;
		assunto = "Jump";
		codigo = "30";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Walk (20) | Jump (30)", hierarchicalDescription.getValue());
		
		index = 3;
		assunto = "Hop";
		codigo = "40";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Walk (20) | Jump (30) | Hop (40)", hierarchicalDescription.getValue());	

		index = 4;
		assunto = "Climb";
		codigo = "50";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Walk (20) | Jump (30) | Hop (40) | Climb (50)", 
			hierarchicalDescription.getValue());

		index = 5;
		assunto = "NOT ALLOWED 2";
		codigo = "-1";
		hierarchicalDescription.setValue(index, assunto, codigo);
		assertEquals("Run (10) | Walk (20) | Jump (30) | Hop (40) | Climb (50)", 
			hierarchicalDescription.getValue());
    }

	@Test
    public void testChangeLevelHierarchicalDescription() {
		int index = 0;
		String assunto = "Run";
		String codigo = "10";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10)", hierarchicalDescription.getValue());

		index = 1;
		assunto = "Walk";
		codigo = "20";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Walk (20)", hierarchicalDescription.getValue());

		index = 2;
		assunto = "Jump";
		codigo = "30";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Walk (20) | Jump (30)", hierarchicalDescription.getValue());
		
		index = 1;
		assunto = "Hop";
		codigo = "40";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Hop (40)", hierarchicalDescription.getValue());	

		index = 2;
		assunto = "Climb";
		codigo = "50";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Run (10) | Hop (40) | Climb (50)", hierarchicalDescription.getValue());

		index = 0;
		assunto = "Swimming";
		codigo = "100";
		hierarchicalDescription.setValue(index, assunto, codigo);
        assertEquals("Swimming (100)", hierarchicalDescription.getValue());
    }
}
