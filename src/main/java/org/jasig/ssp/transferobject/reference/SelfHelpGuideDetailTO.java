package org.jasig.ssp.transferobject.reference;

import java.io.Serializable;
import java.util.List;

import org.jasig.mygps.model.transferobject.SelfHelpGuideQuestionTO;
import org.jasig.ssp.model.reference.SelfHelpGuide;
import org.jasig.ssp.model.reference.SelfHelpGuideQuestion;

import com.google.common.collect.Lists;

public class SelfHelpGuideDetailTO extends SelfHelpGuideTO implements
		Serializable {

	private static final long serialVersionUID = 4097508291481320856L;

	private String introductoryText;

	private List<SelfHelpGuideQuestionTO> questions;

	public SelfHelpGuideDetailTO() {
		super();
	}

	public SelfHelpGuideDetailTO(final SelfHelpGuide model) {
		super();
		from(model);
	}

	@Override
	public final void from(final SelfHelpGuide model) {
		super.from(model);

		introductoryText = model.getIntroductoryText();

		questions = Lists.newArrayList();
		for (SelfHelpGuideQuestion question : model.getSelfHelpGuideQuestions()) {
			questions.add(new SelfHelpGuideQuestionTO(question)); // NOPMD
		}
	}

	public String getIntroductoryText() {
		return introductoryText;
	}

	public void setIntroductoryText(final String introductoryText) {
		this.introductoryText = introductoryText;
	}

	public List<SelfHelpGuideQuestionTO> getQuestions() {
		return questions;
	}

	public void setQuestions(final List<SelfHelpGuideQuestionTO> questions) {
		this.questions = questions;
	}
}