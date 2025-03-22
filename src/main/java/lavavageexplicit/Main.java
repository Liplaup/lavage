package lavavageexplicit;

import java.util.*;
import java.io.*;

public class Lavage {

	public static void main(String[] args) {
		// La ressource partagée
		Pile pileAssiettes = new Pile();
		// Les threads qui se partagent la ressource
		Laveur l = new Laveur(pileAssiettes);
		Laveur l2 = new Laveur(pileAssiettes);
		Essuyeur e = new Essuyeur(pileAssiettes);
		//Essuyeur e2 = new Essuyeur(pileAssiettes);
		//Essuyeur e3 = new Essuyeur(pileAssiettes);

		l.start();
		e.start();
		l2.start();
		//e2.start();
		// e3.start();
		// attendre la frappe d’une touche dans la console
		try {
			System.in.read();
		} catch (IOException ex) {
		}

		l.interrupt();
		e.interrupt();
		l2.interrupt();
		//e2.interrupt();
		//e3.interrupt();

	}
}
