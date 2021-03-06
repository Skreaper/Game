package com.antimeta.game.util;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.DatabaseAccessor;
import com.antimeta.game.ormlite.entity.Stage;

/**
 * @author Darius Wattimena
 *         on 01-Jan-17.
 */
public class StageUtil {
    private static final DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    public static Stage checkStageIfNewNeeded(Stage currentStage){
        Integer killsNeeded = currentStage.getKillsNeeded();
        if(killsNeeded != 0 && killsNeeded != 1){
            currentStage.setKillsNeeded(killsNeeded - 1);
            return currentStage;
        }
        else{
            return createNewStage(currentStage.getLevel());
        }
    }

    public static Stage createNewStage(Integer oldLevel){
        databaseAccessor.stageDM.deleteAll();
        Stage stage = new Stage();
        stage.setName(RandomValues.getStageName());
        stage.setKillsNeeded(10);
        stage.setLevel(oldLevel + 1);
        databaseAccessor.stageDM.save(stage);
        return stage;
    }

    public static Stage createNewLevelOneStage(){
        databaseAccessor.stageDM.deleteAll();
        Stage stage = new Stage();
        stage.setName(RandomValues.getStageName());
        stage.setKillsNeeded(10);
        stage.setLevel(1);
        databaseAccessor.stageDM.save(stage);
        return stage;
    }
}
