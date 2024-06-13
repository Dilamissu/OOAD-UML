package function_graphic.enums;

import function_graphic.UseCase;
import function_graphic.Association;
import function_graphic.Class;
import function_graphic.Composition;
import function_graphic.Generalization;
import function_graphic.base_objects.UMLLine;
import function_graphic.base_objects.UMLObject;

public enum ToolType{
    SELECT,
    ASSOCIATION,
    GENERALIZATION,
    COMPOSITION,
    CLASS,
    USECASE;

    public UMLObject createObject(int x, int y){
        switch(this){
            case CLASS:
                return new Class(x, y);
            case USECASE:
                return new UseCase(x, y);
            default:
                return null;
        }
    }

    public UMLLine createLine(int xPressed, int yPressed, int xReleased, int yReleased, UMLObject from, UMLObject to){
        switch(this){
            case ASSOCIATION:
                return new Association(xPressed, yPressed, xReleased, yReleased, from, to);
            case GENERALIZATION:
                return new Generalization(xPressed, yPressed, xReleased, yReleased, from, to);
            case COMPOSITION:
                return new Composition(xPressed, yPressed, xReleased, yReleased, from, to);
            default:
                return null;
        }
    }

    public boolean isObject(){
        switch(this){
            case CLASS:
            case USECASE:
                return true;
            default:
                return false;
        }
    }

    public boolean isLine(){
        switch(this){
            case ASSOCIATION:
            case GENERALIZATION:
            case COMPOSITION:
                return true;
            default:
                return false;
        }
    }

    public boolean isSelect(){
        return this == SELECT;
    }
}