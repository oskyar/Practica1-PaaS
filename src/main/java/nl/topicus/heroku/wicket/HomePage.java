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

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		feedbackPanel.setOutputMarkupId(true);

		Form<?> form = new Form("form");

		Label pregunta = new Label(
				"pregunta",
				"¿Qué tipo de virtualización requiere la modificación de los sistemas operativos?");
		form.add(pregunta);

		final TextField<String> areaRespuesta = new TextField("respuesta", new Model(""));
		areaRespuesta.setOutputMarkupId(true);
		areaRespuesta.setRequired(true);
		form.add(areaRespuesta);
		Button evaluar = new Button("evaluar") {
			public void onSubmit() {
				String respuesta = areaRespuesta.getModelObject();
				if (respuesta.equals("paravirtualizacion")) {
					feedbackPanel
							.info("¡Enhorabuena! ¡Has aprendido mucho con JJ!");
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
