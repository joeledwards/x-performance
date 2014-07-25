package structure;
import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 * This class provides a simplified (sort of) mechanism for associating
 * properties of any type with an enumeration constant internally to an
 * enumeration.
 * </p>
 * An example enum should follow this example:
 * </p>
 * <pre>
 * public enum ExampleEnum {
 *     CONSTANT1("property1"),
 *     CONSTANT2("property2"),
 *     CONSTANT3("property3");
 *     private static EnumProperty<ExampleEnum, String> descriptionProperty;
 * 
 *     private ExampleEnum(String description) {
 *         putDescription(description, this);
 *     }
 * 
 *     private static void putDescription(String description, ExampleEnum type) {
 *         if (descriptionProperty == null)
 *             descriptionProperty = EnumProperty.newInstance();
 * 
 *         descriptionProperty.addPair(type, description);
 *     }
 * 
 *     public static ExampleEnum forDescription(String description) {
 *         return descriptionProperty.getConstant(description);
 *     }
 * 
 *     public String getDescription() {
 *         return descriptionProperty.getProperty(this);
 *     }
 * }
 *  </pre>
 *  </p>
 *  The correct practice is to create the following three methods for each property:
 *    - private static void put<PropertyName>(P propertyValue, C constant)
 *    - public P get<PropertyName>()
 *    - public C for<PropertyName>(P propertyValue)
 *  </p>
 * @author Joel Edwards <joeledwards@loadstorm.com>
 * @since 2013-03-07
 *  </p>
 * @param <C>  The enumeration constant to associate with the property.
 * @param <P>  The property to associate with the enumeration constant.
 */
public class EnumProperty<C extends Enum<?>, P>
{
	private HashMap<P,C> propertyMap;
	private IdentityHashMap<C,P> enumMap;

	private EnumProperty()
	{
		propertyMap = new HashMap<P,C>();
		enumMap = new IdentityHashMap<C,P>();
	}

	/**
	 * Convenience method for creating a new instance of EnumProperty with the appropriate parameters.
	 * 
	 * @return newly created EnumProperty matching the parameters of the assignment.
	 */
	public static <T extends Enum<?>,V> EnumProperty<T,V> newInstance()
	{
		return new EnumProperty<T,V>();
	}

	/**
	 * Add a new pair mapping an enum constant to a property.
	 * 
	 * @param constant the enum constant.
	 * @param property the property.
	 */
	public void addPair(C constant, P property)
	{
		propertyMap.put(property, constant);
		enumMap.put(constant, property);
	}

	/**
	 * Get the property associated with the supplied enum constant.
	 * 
	 * @param constant the constant whose property should be returned.
	 * 
	 * @return the property associated with the specified constant, or <tt>null</tt> if there was no property
	 * associated with the supplied constant.
	 */
	public P getProperty(C constant)
	{
		return enumMap.get(constant);
	}

	/**
	 * Get the enum constant associated with the supplied property.
	 * 
	 * @param property the property whose enum constant should be returned.
	 * 
	 * @return the enum constant associated with the specified property, or <tt>null</tt> if there was no
	 * enum constant associated with the supplied property.
	 */
	public C getConstant(P property)
	{
		return propertyMap.get(property);
	}
}