package com.leopard.karol.leopardtrainings;

/** Author: Karol Pasierb - Software Engineering - 40270305
 * Created by Karol on 2017-03-09.
 *
 * Description:
 * This class contains the code for a specific part of a training.
 * Mobilization is
 * On this screen user creates new training plans. Choosing the areas to work with and specific exercises
 *
 * Future updates:
 *
 * Design Patterns Used:
 *
 * Last Update: 09/03/2017
 */

public class Mobilization {

    //instance fields with getters and setters
    private String mobilizationArea;
    public String getMobilizationArea() {
        return mobilizationArea;
    }
    public void setMobilizationArea(String mobilizationArea) {
        this.mobilizationArea = mobilizationArea;
    }




    private String[][] mobilizationsInAreas = {
            {"T-Spine Smash Extension", "T-Spine\tSmash:\tSide-to-Side",
            "T-Spine\tSmash:\tOverhead\tExtension\tBias",
            "T-Spine\tKeg:\tOverhead\tAnchor\twith\tExtension\nBias",
    "T-Spine\tSmash:\tInternal\tRotation"},{"Overhead\tTissue\tSmash"},
            {"Banded\tBully", "Bully\tExtension\tBias"},
            {"Banded\tElbow\tExtension"},
            {"Low\tBack\tSmash:\tSingle\tLacrosse\tBall",      "Classic\tSpinal\tTwist"},
            {"Glute\tSmash\tand\tFloss"},
            {"Quad\tSmash", "Suprapatella\tSmash\tand\tFloss"},
            {"Olympic\tWall\tSquat"},
            {"Posterior\tChain\tFloss"},
            {"Inside\tLine"},
            {"Lateral\tand\tAnterior\tCompartment\tSmash"},
            {"Bone\tSaw\tCalf\tSmash"},
            {"Forefoot\tMobilization"}};




    private String mobilizationName;
    public String getMobilizationName() {
        return mobilizationName;
    }
    public void setMobilizationName(String mobilizationName) {
        this.mobilizationName = mobilizationName;
    }

    private int mobilizationTime;
    public int getMobilizationTime() {
        return mobilizationTime;
    }
    public void setMobilizationTime(int mobilizationTime) {
        this.mobilizationTime = mobilizationTime;
    }

    private int totalTime;
    public int getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }


    public Mobilization() {
    }
}
