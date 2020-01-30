package me.snowman.betterssentials.managers;

import me.snowman.betterssentials.Betterssentials;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EconomyImplementer implements Economy {
    private final FileManager fileManager = Betterssentials.fileManager;
    public HashMap<UUID, Double> playerBank = new HashMap<>();

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "Betterssentials";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return String.format(String.valueOf(v));
    }

    @Override
    public String currencyNamePlural() {
        return null;
    }

    @Override
    public String currencyNameSingular() {
        return fileManager.getConfig().getString("Money-Sign");
    }

    @Override
    public boolean hasAccount(String s) {
        UUID player = Bukkit.getPlayer(s).getUniqueId();
        return fileManager.getPlayer(player).isSet("values.balance");
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        UUID uuid = offlinePlayer.getUniqueId();
        return fileManager.getPlayer(uuid).isSet("values.balance");
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        UUID player = Bukkit.getPlayer(s).getUniqueId();
        return fileManager.getPlayer(player).isSet("values.balance");
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        UUID uuid = offlinePlayer.getUniqueId();
        return fileManager.getPlayer(uuid).isSet("values.balance");
    }

    @Override
    public double getBalance(String s) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        return playerBank.get(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        UUID uuid = offlinePlayer.getUniqueId();
        return fileManager.getPlayer(uuid).getInt("values.balance");
    }

    @Override
    public double getBalance(String s, String s1) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        return playerBank.get(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        UUID uuid = offlinePlayer.getUniqueId();
        return fileManager.getPlayer(uuid).getInt("values.balance");
    }

    @Override
    public boolean has(String s, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double balance = playerBank.get(uuid);
        return balance >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double balance = fileManager.getPlayer(uuid).getDouble("values.balance");
        return balance >= v;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double balance = playerBank.get(uuid);
        return balance >= v;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double balance = fileManager.getPlayer(uuid).getDouble("values.balance");
        return balance >= v;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = playerBank.get(uuid);
        EconomyResponse response;
        if (has(player, v)) {
            playerBank.put(uuid, oldBalance - v);
            fileManager.getPlayer(uuid).set("values.balance", oldBalance - v);
            fileManager.savePlayer();
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
            response.transactionSuccess();
        } else {
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.FAILURE, "Nor Enough Money");
        }
        return response;

    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = playerBank.get(uuid);
        EconomyResponse response;
        if (has(offlinePlayer, v)) {
            playerBank.put(uuid, oldBalance - v);
            fileManager.getPlayer(uuid).set("values.balance", oldBalance - v);
            fileManager.savePlayer();
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
            response.transactionSuccess();
        } else {
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.FAILURE, "Not Enough Money");
        }
        return response;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        double oldBalance = playerBank.get(uuid);
        EconomyResponse response;
        if (has(player, v)) {
            playerBank.put(uuid, oldBalance - v);
            fileManager.getPlayer(uuid).set("values.balance", oldBalance - v);
            fileManager.savePlayer();
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
            response.transactionSuccess();
        } else {
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.FAILURE, "Not Enough Money");
        }
        return response;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        double oldBalance = playerBank.get(uuid);
        EconomyResponse response;
        if (has(offlinePlayer, v)) {
            playerBank.put(uuid, oldBalance - v);
            fileManager.getPlayer(uuid).set("values.balance", oldBalance - v);
            fileManager.savePlayer();
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
            response.transactionSuccess();
        } else {
            response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.FAILURE, "Not Enough Money");
        }
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        if (playerBank.get(uuid) == null) {
            playerBank.put(uuid, fileManager.getPlayer(uuid).getDouble("values.balance"));
        }
        double oldBalance = playerBank.get(uuid);
        playerBank.put(uuid, oldBalance + v);
        fileManager.getPlayer(uuid).set("values.balance", oldBalance + v);
        fileManager.savePlayer();
        EconomyResponse response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
        response.transactionSuccess();
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        if (playerBank.get(uuid) == null) {
            playerBank.put(uuid, fileManager.getPlayer(uuid).getDouble("values.balance"));
        }
        double oldBalance = playerBank.get(uuid);
        playerBank.put(uuid, oldBalance + v);
        fileManager.getPlayer(uuid).set("values.balance", oldBalance + v);
        fileManager.savePlayer();
        EconomyResponse response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
        response.transactionSuccess();
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        Player player = Bukkit.getPlayer(s);
        UUID uuid = player.getUniqueId();
        if (playerBank.get(uuid) == null) {
            playerBank.put(uuid, fileManager.getPlayer(uuid).getDouble("values.balance"));
        }
        double oldBalance = playerBank.get(uuid);
        playerBank.put(uuid, oldBalance + v);
        fileManager.getPlayer(uuid).set("values.balance", oldBalance + v);
        fileManager.savePlayer();
        EconomyResponse response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
        response.transactionSuccess();
        return response;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        UUID uuid = offlinePlayer.getUniqueId();
        if (playerBank.get(uuid) == null) {
            playerBank.put(uuid, fileManager.getPlayer(uuid).getDouble("values.balance"));
        }
        double oldBalance = playerBank.get(uuid);
        playerBank.put(uuid, oldBalance + v);
        fileManager.getPlayer(uuid).set("values.balance", oldBalance + v);
        fileManager.savePlayer();
        EconomyResponse response = new EconomyResponse(v, playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, "Succes");
        response.transactionSuccess();
        return response;
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }
}