/**
  Esta archivo pertenece a la aplicación "generador de objetivos" bajo licencia GPLv2.
  Copyright (C) 2013 Óscar R. Zafra Megías.

  Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos 
  de la Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
  bien de la versión 2 de dicha Licencia o bien (según su elección) de cualquier versión 
  posterior.

  Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
  incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN 
  PROPÓSITO PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.

  Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
  Si no ha sido así, escriba a la Free Software Foundation, Inc., en 675 Mass Ave, Cambridge, 
  MA 02139, EEUU.
*/

package nl.topicus.heroku.wicket;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import java.util.ArrayList;
import java.util.Random;
import java.util.Locale;
import java.util.Calendar;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	public HomePage(final PageParameters parameters) {
		final ArrayList<String> preguntas= new ArrayList<String>();
		preguntas.add("¿Qué tipo de virtualización requiere la modificación de los sistemas operativos?");
		preguntas.add("¿Con qué aplicación hemos restringido, reservado, limintado recursos al sistema?");
		preguntas.add("¿Qué archivo de configuración teníamos que modificar para dar menos prioridad a los procesos tanto de usuario como de sistema?");
		preguntas.add("¿Con qué programa se ve la carga real de los programas/procesos y se pueden comprobar los efectos de migración de tareas pesadas?");
		preguntas.add("¿Con qué orden podíamos saber si nuestro pc soporta virtualización?");
		preguntas.add("¿Qué gestor de fuentes estamos utilizando actualmente en la asignatura?");

		final ArrayList<String> respuestas= new ArrayList<String>();
		respuestas.add("paravirtualizacion");
		respuestas.add("cgroups");
		respuestas.add("cgcreate");
		respuestas.add("htop");
		respuestas.add("kvm-ok");
		respuestas.add("git");

		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		feedbackPanel.setOutputMarkupId(true);

//		final int indice = (int)Math.random()*6;
		Locale locale = new Locale("Spain");
		Random r = new Random(Calendar.getInstance(locale).getTimeInMillis()*25/100);
		final int indice = r.nextInt(6);
		String pregunta = preguntas.get(indice);
		Label labelPregunta = new Label(
				"pregunta",
				pregunta);
		Form<?> form = new Form("form");
		form.add(labelPregunta);

		final TextField<String> areaRespuesta = new TextField("respuesta", new Model(""));
		areaRespuesta.setOutputMarkupId(true);
		areaRespuesta.setRequired(true);
		form.add(areaRespuesta);
		Button evaluar = new Button("evaluar") {
			public void onSubmit() {
				String respuesta = areaRespuesta.getModelObject();
				if (respuesta.equals(respuestas.get(indice))) {
					feedbackPanel
							.info("¡Enhorabuena! ¡Has aprendido mucho con JJ!\n Atención a la nueva pregunta");
				} else {
					feedbackPanel
							.error("¡Error! Hay que estudiar un poquito más...");
				}
			}
		};
		form.add(evaluar);
		add(form);
	}
}
