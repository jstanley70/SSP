package org.studentsuccessplan.ssp.transferobject.reference;

import java.util.List;
import java.util.UUID;

import org.studentsuccessplan.ssp.model.reference.Category;
import org.studentsuccessplan.ssp.transferobject.TransferObject;

import com.google.common.collect.Lists;

public class CategoryTO extends AbstractReferenceTO<Category> implements
		TransferObject<Category> {

	public CategoryTO() {
		super();
	}

	public CategoryTO(final UUID id) {
		super(id);
	}

	public CategoryTO(final UUID id, final String name) {
		super(id, name);
	}

	public CategoryTO(final UUID id, final String name, final String description) {
		super(id, name, description);
	}

	public CategoryTO(final Category model) {
		super();
		fromModel(model);
	}

	@Override
	public Category addToModel(final Category model) {
		super.addToModel(model);
		return model;
	}

	@Override
	public Category asModel() {
		return addToModel(new Category());
	}

	public static List<CategoryTO> listToTOList(final List<Category> models) {
		final List<CategoryTO> tos = Lists.newArrayList();
		for (Category model : models) {
			tos.add(new CategoryTO(model));
		}
		return tos;
	}
}