package fr.snekkja.cours.webservices.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Un article.
 * 
 * @author Snekkja
 * @since 10 avr. 2016
 */
public class Article implements Serializable
{
	
	/**
	 * Identifiant de version
	 * 
	 * @see Serializable
	 */
	private static final long	serialVersionUID	= 1L;
	
	/** Référence unique */
	private String				reference;
	
	/** Label */
	private String				label;
	
	/** Description */
	private String				description;
	
	/** {@link CategorieArticle Catégorie de l'article} */
	private CategorieArticle	categorieArticle;
	
	/** Prix HT */
	private BigDecimal			prix;
	
	public String getReference()
	{
		return reference;
	}
	
	public void setReference(final String reference)
	{
		this.reference = reference;
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
	
	public CategorieArticle getCategorieArticle()
	{
		return categorieArticle;
	}
	
	public void setCategorieArticle(final CategorieArticle categorieArticle)
	{
		this.categorieArticle = categorieArticle;
	}
	
	public BigDecimal getPrix()
	{
		return prix;
	}
	
	public void setPrix(final BigDecimal prix)
	{
		this.prix = prix;
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
		if (!(candidat instanceof Article))
			return false;
		
		final Article autre = (Article) candidat;
		
		return new EqualsBuilder() //
				.append(this.categorieArticle, autre.categorieArticle) //
				.append(this.description, autre.description) //
				.append(this.label, autre.label) //
				.append(this.prix, autre.prix) //
				.append(this.reference, autre.reference) //
				.build();
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder() //
				.appendSuper(super.hashCode()) //
				.append(this.categorieArticle) //
				.append(this.description) //
				.append(this.label) //
				.append(this.prix) //
				.append(this.reference) //
				.build();
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this) //
				.append("Référence", this.reference) //
				.append("Label", this.label) //
				.append("Prix", this.prix) //
				.append("Description", this.description) //
				.append("Catégorie", this.categorieArticle) //
				.build();
	}
	
}
