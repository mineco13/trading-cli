# trading-cli

The code in this repository was written in 2019.

I tried to apply the _Clean Architecture_ using [Kotlin](https://kotlinlang.org/docs/comparison-to-java.html).

## Kotlin is an excellent [Null safety](https://kotlinlang.org/docs/null-safety.html#nullable-types-and-non-nullable-types) language that has 100% [interoperability](https://kotlinlang.org/docs/java-to-kotlin-interop.html) with Java.

**Kotlin** allows for writing code **shorter** than Java, thanks to [features](https://kotlinlang.org/docs/idioms.html) like [type inference](https://kotlinlang.org/docs/basic-syntax.html#variables) and [expression-oriented](https://kotlinlang.org/docs/control-flow.html) programming.

<details>
<summary>Java 8</summary>

```Java
class Playground {
    public static void main(String[ ] args) {
        boolean bool = true;
        int value = bool ? 1 : 2;
        String name;
        switch (value) {
            case 0:
                name = "armadillo";
                break;
            case 1:
                name = "beaver";
                break;
            case 2:
                name = "cat";
                break;
            default:
                name = "other";
        }
        Animal<String> animal = new Animal<String>(name);
        animal.setName("man");
        System.out.println(animal.getName() + " is animal.");
    }
}

class Animal<T extends String>{
    private T name;

    Animal(T name){
        this.name=name;
    }

    public T getName(){
        return name;
    }

    public void setName(T name){
        this.name = name;
    }
}
```

</details>

<details open>
<summary>Kotlin</summary>

```Kotlin
fun main(args: Array<String>) {
    val bool: Boolean = true
    val value = if (bool) 1 else 2

    val name = when (value) {
        0 -> "armadillo"
        1 -> "beaver"
        2 -> "cat"
        else -> "other"
    }

    val animal = Animal(name)
    animal.name = "man"
    println("${animal.name} is animal.")
}

class Animal<T: String>(var name: T)
```

</details>

And **Kotlin** keeps the code **clean** because it focuses on [immutability](https://kotlinlang.org/docs/coding-conventions.html#immutability) and [delegation](https://kotlinlang.org/docs/delegation.html), not inheritance.

<details>
<summary>Java 8</summary>

```Java
class Playground {
    public static void main(String[] args) {
        MetricSpace delegatee = new MetricSpace(){
            @Override
            public int distance(int a,int b){
                return Math.abs(b-a);
            }
        };
        MetricSpace delegator = new MetricSpaceImpl(delegatee);
        System.out.println("Distance is " + delegator.distance(5,3));
    }
}

interface MetricSpace{
    int distance(int a,int b);
}

class MetricSpaceImpl implements MetricSpace{
    private MetricSpace space;
    MetricSpaceImpl(MetricSpace space){
        this.space=space;
    }
    public int distance(int a,int b){
        return space.distance(a,b);
    }
}
```

</details>

<details open>
<summary>Kotlin</summary>

```Kotlin
import java.util.*

fun main() {
    val delegatee = object : MetricSpace {
        override fun distance(a: Int, b: Int) = Math.abs(b - a)
    }
    val delegator = MetricSpaceImpl(delegatee)
    println("Distance is ${delegator.distance(5, 3)}")
}

interface MetricSpace {
    fun distance(a: Int, b: Int): Int
}

class MetricSpaceImpl(space: MetricSpace) : MetricSpace by space
```

</details>

[Let’s apply Kotlin](https://kotlinlang.org/docs/server-overview.html#frameworks-for-server-side-development-with-kotlin) not Java to write the code even if you had already written using Java! ([You can compile the code mixed Java and Kotlin!](https://kotlinlang.org/docs/java-interop.html))

> If you use IntelliJ IDEA, You can paste Java code to \*.kt file and convert it to Kotlin automatically, or [you can convert all Java files to Kotlin.](https://www.jetbrains.com/help/idea/get-started-with-kotlin.html#ab15d972)  
> Or you can use [the convert tool online](https://www.javainuse.com/java2kot).
