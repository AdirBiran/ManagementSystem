package Service;

import Domain.AssetManagement;
import Presentation.FootballManagementSystem;

public class AssetSystem {

    private AssetManagement assetManagement;
    private FootballManagementSystem system;

    public AssetSystem(AssetManagement assetManagement, FootballManagementSystem system) {
        this.assetManagement = assetManagement;
        this.system = system;
    }
}
