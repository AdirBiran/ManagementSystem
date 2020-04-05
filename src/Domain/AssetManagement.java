package Domain;

import Data.Database;
import Service.AssetSystem;

public class AssetManagement {
    private Database database;
    private AssetSystem assetSystem;

    public AssetManagement(Database database, AssetSystem assetSystem) {
        this.database = database;
        this.assetSystem = assetSystem;
    }
}
