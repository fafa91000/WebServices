package fr.snekkja.cours.webservices.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Une catégorie d'{@link Article article}.
 * 
 * @author Snekkja
 * @since 10 avr. 2016
 */
public class CategorieArticle
{
	
	/** Code unique */
	private String	code;
	
	/** Label */
	private String	label;
	
	/** Description */
	private String	description;
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(final String code)
	{
		this.code = code;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public void setLabel(final String label)
	{
		this.label = label;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(final String description)
	{
		this.description = description;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object candidat)
	{
		
		if (candidat == null)
			return false;
		if (candidat == this)
			return true;
		if (!(candidat instanceof CategorieArticle))
			return false;
		
		final CategorieArticle autre = (CategorieArticle) candidat;
		
		return new EqualsBuilder() //
				.append(this.code, autre.code) //
				.append(this.description, autre.description) //
				.append(this.label, autre.label) //
				.build();
	}
	
	@Override
	public int hashCode()
	{
		// TODO
		return super.hashCode();
	}
	
	@Override
	public String toString()
	{
		// TODO
		return super.toString();
	}
	
}
