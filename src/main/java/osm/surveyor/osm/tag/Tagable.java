package osm.surveyor.osm.tag;

import java.util.List;

import osm.surveyor.osm.TagBean;

public interface Tagable {
	
	default TagBean getTag(String key) {
		for (TagBean tag : getTagList()) {
			if ((tag.k != null) && tag.k.equals(key)) {
				return tag;
			}
		}
		return null;
	}
	
	default void addTag(String k, String v) {
		addTag(new TagBean(k, v));
	}

	default void addTag(TagBean tag) {
		if (tag != null) {
			if (getTag(tag.k) != null) {
				removeTag(tag.k);
			}
			if ((tag.v != null) && !tag.v.isEmpty()) {
				getTagList().add(tag);
			}
		}
	}
	
    default void removeTagAll() {
    	getTagList().clear();
    }

	default void removeTag(String key) {
		TagBean tag = getTag(key);
		if (tag != null) {
			getTagList().remove(tag);
		}
	}
	
    default String getTagValue(String key) {
		TagBean tag = getTag(key);
		if (tag == null) {
			return null;
		}
		if (tag.v == null) {
			return null;
		}
		if (tag.v.isEmpty()) {
			return null;
		}
		return tag.v;
	}

	public List<TagBean> getTagList();
}
