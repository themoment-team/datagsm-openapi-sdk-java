package team.themoment.datagsm.sdk.openapi.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 급식 정보
 */
public class Meal {
    private String mealId;
    private String schoolCode;
    private String schoolName;
    private String officeCode;
    private String officeName;
    private LocalDate mealDate;
    private MealType mealType;
    private List<String> mealMenu = new ArrayList<>();
    private List<String> mealAllergyInfo = new ArrayList<>();
    private String mealCalories;
    private String originInfo;
    private String nutritionInfo;
    private Integer mealServeCount;

    public Meal() {}

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public List<String> getMealMenu() {
        return mealMenu;
    }

    public void setMealMenu(List<String> mealMenu) {
        this.mealMenu = mealMenu;
    }

    public List<String> getMealAllergyInfo() {
        return mealAllergyInfo;
    }

    public void setMealAllergyInfo(List<String> mealAllergyInfo) {
        this.mealAllergyInfo = mealAllergyInfo;
    }

    public String getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(String mealCalories) {
        this.mealCalories = mealCalories;
    }

    public String getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(String originInfo) {
        this.originInfo = originInfo;
    }

    public String getNutritionInfo() {
        return nutritionInfo;
    }

    public void setNutritionInfo(String nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    public Integer getMealServeCount() {
        return mealServeCount;
    }

    public void setMealServeCount(Integer mealServeCount) {
        this.mealServeCount = mealServeCount;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId='" + mealId + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", officeName='" + officeName + '\'' +
                ", mealDate=" + mealDate +
                ", mealType=" + mealType +
                ", mealMenu=" + mealMenu +
                ", mealAllergyInfo=" + mealAllergyInfo +
                ", mealCalories='" + mealCalories + '\'' +
                ", originInfo='" + originInfo + '\'' +
                ", nutritionInfo='" + nutritionInfo + '\'' +
                ", mealServeCount=" + mealServeCount +
                '}';
    }
}