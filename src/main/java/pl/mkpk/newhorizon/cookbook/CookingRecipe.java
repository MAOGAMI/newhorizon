package pl.mkpk.newhorizon.cookbook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingRecipe {

    public String name;

    List<IngedientResource> ingredients;
    List<PreparationStep> preparationSteps;
    public int numberOfPortions;
    public BigDecimal kalories;

    private CookingRecipe(RecipeBuilder builder) {
        this.name = builder.name;
        this.ingredients = builder.ingredients;
        this.preparationSteps = builder.preparationSteps;
        this.numberOfPortions = 1;

        this.kalories = calculateCalories();
    }

    private BigDecimal calculateCalories() {
        BigDecimal result=new BigDecimal(0);

        for (IngedientResource ingrediendStack:ingredients) {
            result=result.add(ingrediendStack.calculateCalories());
        }
        return result;
    }

    public static class RecipeBuilder {
        private String name;
        private List<IngedientResource> ingredients;
        private List<PreparationStep> preparationSteps;
        IngedientsBase base;

        //int numberOfPortions;

        boolean isName;
        boolean isIngredients;
        boolean isPreparationStep;

        public RecipeBuilder(IngedientsBase base){
            this.base=base;

            name="";
            ingredients=new ArrayList<>();
            preparationSteps=new ArrayList<>();
            isName=false;
            isIngredients=false;
            isPreparationStep=false;
        }

        public RecipeBuilder setName(String name){
            this.name=name;
            isName=true;
            return this;
        }

        public RecipeBuilder addIngredients(String ingredientName, BigDecimal numOf){

            this.ingredients.add(new IngedientResource(
                    base.getIngredient(ingredientName),
                    numOf));
            this.isIngredients=true;
            return this;
        }

        public RecipeBuilder addPreparationStep(String descriptor,BigDecimal minutes){

            this.preparationSteps.add(new PreparationStep(descriptor,minutes));
            this.isPreparationStep=true;
            return this;
        }

        public CookingRecipe build(){
            if(isName==true && isIngredients==true && isPreparationStep==true){
                return new CookingRecipe(this);
            }else{
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "CookingRecipe{" +
                "name='" + this.name + '\'' +
                ", ingredients=" + this.ingredients +
                ", preparationSteps=" + this.preparationSteps +
                ", numberOfPortions=" + this.numberOfPortions +
                ", kalories=" + this.kalories +
                '}';
    }
}
