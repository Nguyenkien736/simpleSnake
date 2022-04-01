package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class CastingDirector {
    ArrayList<Actor>CURRENT_CAST;
    ArrayList<Actor>COLLISION_CHECKLIST;
    HashSet<Actor>REMOVED_ACTOR;
    public CastingDirector()
    {
        CURRENT_CAST=new ArrayList<>();
        COLLISION_CHECKLIST=new ArrayList<>();
        REMOVED_ACTOR=new HashSet<>();
    }

    public void addToCurrentList(Actor...actors)
    {
        CURRENT_CAST.addAll(Arrays.asList(actors));
    }
    public void removeFromCurrentCast(Actor...actors)
    {
        CURRENT_CAST.removeAll(Arrays.asList(actors));
    }
    public void addToCollisionCheckList(Actor...actors)
    {
        COLLISION_CHECKLIST.addAll(Arrays.asList(actors));
    }
    public void resetCollideCheckList() {
        COLLISION_CHECKLIST.clear();
        COLLISION_CHECKLIST.addAll(CURRENT_CAST);
    }

    public ArrayList<Actor> getCURRENT_CAST() {
        return CURRENT_CAST;
    }
    public void resetRemovedActors() {
        CURRENT_CAST.removeAll(REMOVED_ACTOR);
        REMOVED_ACTOR.clear();
    }

    public ArrayList<Actor> getCOLLISION_CHECKLIST() {
        return COLLISION_CHECKLIST;
    }

    public HashSet<Actor> getREMOVED_ACTOR() {
        return REMOVED_ACTOR;
    }
}
