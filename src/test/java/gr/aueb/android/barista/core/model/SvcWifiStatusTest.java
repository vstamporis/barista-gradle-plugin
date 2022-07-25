package gr.aueb.android.barista.core.model;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.stream.Stream;

import static org.junit.Assert.*;


public class SvcWifiStatusTest {

    @Test
    public void parseResultIfConnectedApi30(){
        SvcWifiStatus wifiStatus = new SvcWifiStatus(null);
        wifiStatus.parseResult(new BufferedReader(new StringReader(DUMP_API30_CONNECTED)).lines());
        assertNotNull(wifiStatus.getStatus());
        assertEquals(wifiStatus.getStatus(), "CONNECTED/CONNECTED");
    }

    @Test
    public void parseResultIfDisconnectedApi30(){
        SvcWifiStatus wifiStatus = new SvcWifiStatus(null);
        wifiStatus.parseResult(new BufferedReader(new StringReader(DUMP_API30_DISCONNECTED)).lines());
        assertNotNull(wifiStatus.getStatus());
        assertEquals(wifiStatus.getStatus(), "DISCONNECTED/DISCONNECTED");
    }


    @Test
    public void parseResultIfConnected() {

        SvcWifiStatus wifiStatus = new SvcWifiStatus(null);
        String testString = "rec[14]: time=06-20 20:03:41.080 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)\n" +
                " rec[15]: time=06-20 20:05:38.636 processed=DefaultState org=CompletedState dest=UninitializedState what=131183(0x2006f)\n" +
                " rec[16]: time=06-20 20:05:42.298 processed=DefaultState org=UninitializedState dest=HandshakeState what=147462(0x24006)\n" +
                " rec[17]: time=06-20 20:05:42.330 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)\n" +
                " rec[18]: time=06-20 20:06:07.752 processed=DefaultState org=CompletedState dest=UninitializedState what=131183(0x2006f)\n" +
                " rec[19]: time=06-20 20:06:11.409 processed=DefaultState org=UninitializedState dest=HandshakeState what=147462(0x24006)\n" +
                " rec[20]: time=06-20 20:06:11.442 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)\n" +
                "curState=CompletedState\n" +
                "mAuthFailureInSupplicantBroadcast false\n" +
                "mAuthFailureReason 0\n" +
                "mNetworksDisabledDuringConnect false\n" +
                "\n" +
                "mLinkProperties {InterfaceName: wlan0 LinkAddresses: [fe80::ff:fe44:5566/64,192.168.232.2/21,fec0::ff:fe44:5566/64,fec0::90dd:3c93:5a1c:84e3/64,]  Routes: [fe80::/64 -> :: wlan0,::/0 -> fe80::ff:fe00:100 wlan0,fec0::/64 -> :: wlan0,192.168.232.0/21 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.232.1 wlan0,] DnsAddresses: [10.0.2.3,] UsePrivateDns: false PrivateDnsServerName: null Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}\n" +
                "mWifiInfo SSID: AndroidWifi, BSSID: 02:00:00:00:01:00, MAC: 02:00:00:44:55:66, Supplicant state: COMPLETED, RSSI: -50, Link speed: 58Mbps, Frequency: 2447MHz, Net ID: 0, Metered hint: false, score: 60\n" +
                "mDhcpResults IP address 192.168.232.2/21 Gateway 192.168.232.1  DNS servers: [ 10.0.2.3 ] Domains  DHCP server /192.168.232.1 Vendor info null lease 1476526080 seconds\n" +
                "mNetworkInfo [type: WIFI[], state: CONNECTED/CONNECTED, reason: (unspecified), extra: (none), failover: false, available: true, roaming: false]\n" +
                "mLastSignalLevel 4\n" +
                "mLastBssid 02:00:00:00:01:00\n" +
                "mLastNetworkId 0\n" +
                "mOperationalMode 1\n" +
                "mUserWantsSuspendOpt true\n" +
                "mSuspendOptNeedsDisabled 0\n" +
                "CountryCode sent to driver: US\n" +
                "mConnectionReqCount 3\n" +
                "mUntrustedReqCount 0\n" +
                "Wlan Wake Reasons: totalCmdEventWake 5 totalDriverFwLocalWake -206245696 totalRxDataWake -206245696 rxUnicast 4 rxMulticast 64 rxBroadcast -206557060 icmp -195338852 icmp6 -206466556 icmp6Ra -972025774 icmp6Na -195711691 icmp6Ns 246 ipv4RxMulticast -195338852 ipv6Multicast 8 otherRxMulticast -7588504\n" +
                "\n" +
                "Dump of WifiConfigManager" ;


        new BufferedReader(new StringReader(testString))
                .lines().forEach(line->wifiStatus.parseResult(Stream.of(line)));

        System.out.println(wifiStatus.getStatus());
    }

    @Test
    public void parseResultIfDisconnected() {

        SvcWifiStatus wifiStatus = new SvcWifiStatus(null);
        String testString = "rec[13]: time=06-20 20:03:41.047 processed=DefaultState org=UninitializedState dest=HandshakeState what=147462(0x24006)\n" +
                " rec[14]: time=06-20 20:03:41.080 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)\n" +
                " rec[15]: time=06-20 20:05:38.636 processed=DefaultState org=CompletedState dest=UninitializedState what=131183(0x2006f)\n" +
                " rec[16]: time=06-20 20:05:42.298 processed=DefaultState org=UninitializedState dest=HandshakeState what=147462(0x24006)\n" +
                " rec[17]: time=06-20 20:05:42.330 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)\n" +
                " rec[18]: time=06-20 20:06:07.752 processed=DefaultState org=CompletedState dest=UninitializedState what=131183(0x2006f)\n" +
                " rec[19]: time=06-20 20:06:11.409 processed=DefaultState org=UninitializedState dest=HandshakeState what=147462(0x24006)\n" +
                " rec[20]: time=06-20 20:06:11.442 processed=DefaultState org=HandshakeState dest=CompletedState what=147462(0x24006)\n" +
                "curState=CompletedState\n" +
                "mAuthFailureInSupplicantBroadcast false\n" +
                "mAuthFailureReason 0\n" +
                "mNetworksDisabledDuringConnect false\n" +
                "\n" +
                "mLinkProperties {InterfaceName: wlan0 LinkAddresses: [192.168.232.2/21,]  Routes: [192.168.232.0/21 -> 0.0.0.0 wlan0,0.0.0.0/0 -> 192.168.232.1 wlan0,] DnsAddresses: [10.0.2.3,] UsePrivateDns: false PrivateDnsServerName: null Domains: null MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576}\n" +
                "mWifiInfo SSID: <unknown ssid>, BSSID: <none>, MAC: 02:00:00:44:55:66, Supplicant state: DISCONNECTED, RSSI: -127, Link speed: -1Mbps, Frequency: -1MHz, Net ID: -1, Metered hint: false, score: 0\n" +
                "mDhcpResults IP address Gateway  DNS servers: [ ] Domains  DHCP server /192.168.232.1 Vendor info null lease 0 seconds\n" +
                "mNetworkInfo [type: WIFI[], state: DISCONNECTED/DISCONNECTED, reason: (unspecified), extra: (none), failover: false, available: false, roaming: false]\n" +
                "mLastSignalLevel 4\n" +
                "mLastBssid null\n" +
                "mLastNetworkId -1\n" +
                "mOperationalMode 4\n" +
                "mUserWantsSuspendOpt true\n" +
                "mSuspendOptNeedsDisabled 0\n" +
                "CountryCode sent to driver: US\n" +
                "mConnectionReqCount 3\n" +
                "mUntrustedReqCount 0\n" +
                "Wlan Wake Reasons:null\n" +
                "\n" +
                "Dump of WifiConfigManager\n" +
                "WifiConfigManager - Log Begin ----\n" +
                "2019-06-20T19:34:06.967 - clearInternalData: Clearing all internal data\n" +
                "2019-06-20T19:34:12.182 - setNetworkSelectionStatus: configKey=\"AndroidWifi\"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=06-20 19:34:12.182\n" +
                "2019-06-20T19:36:04.914 - setNetworkSelectionStatus: configKey=\"AndroidWifi\"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=06-20 19:36:04.914\n" +
                "2019-06-20T19:58:20.810 - setNetworkSelectionStatus: configKey=\"AndroidWifi\"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=06-20 19:58:20.809\n" +
                "2019-06-20T20:00:40.530 - setNetworkSelectionStatus: configKey=\"AndroidWifi\"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=06-20 20:00:40.529\n" +
                "2019-06-20T20:03:41.520 - setNetworkSelectionStatus: configKey=\"AndroidWifi\"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=06-20 20:03:41.520\n" +
                "2019-06-20T20:05:42.804 - setNetworkSelectionStatus: configKey=\"AndroidWifi\"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=06-20 20:05:42.803\n" +
                "2019-06-20T20:06:11.904 - setNetworkSelectionStatus: configKey=\"AndroidWifi\"NONE networkStatus=NETWORK_SELECTION_ENABLED disableReason=NETWORK_SELECTION_ENABLE at=time=06-20 20:06:11.904\n" +
                "WifiConfigManager - Log End ----\n" +
                "WifiConfigManager - Configured networks Begin ----\n" +
                "ID: 0 SSID: \"AndroidWifi\" PROVIDER-NAME: null BSSID: null FQDN: null PRIO: 0 HIDDEN: false\n" +
                " NetworkSelectionStatus NETWORK_SELECTION_ENABLED\n" +
                " hasEverConnected: true\n" +
                " numAssociation 9\n" +
                " validatedInternetAccess\n" +
                " KeyMgmt: NONE Protocols: WPA RSN\n" +
                " AuthAlgorithms: OPEN\n" +
                " PairwiseCiphers: TKIP CCMP\n" +
                " GroupCiphers: WEP40 WEP104 TKIP CCMP\n" +
                " PSK: " ;

        new BufferedReader(new StringReader(testString))
                .lines().forEach(line->wifiStatus.parseResult(Stream.of(line)));

        System.out.println(wifiStatus.getStatus());
    }

    public static final String DUMP_API30_CONNECTED = "Wi-Fi is disabled\n" +
            "Verbose logging is off\n" +
            "Stay-awake conditions: 1\n" +
            "mInIdleMode false\n" +
            "mScanPending false\n" +
            "mPersistWifiState 0\n" +
            "StaEventList:\n" +
            "07-22 14:51:08.614 WIFI_ENABLED totalTxBytes=51768 totalRxBytes=27118 screenOn=true cellularData=false\n" +
            "07-22 14:51:16.967 CMD_START_CONNECT totalTxBytes=55006 totalRxBytes=28737 screenOn=true cellularData=false, ConfigInfo: allowed_key_management=1 allowed_protocols=3 allowed_auth_algorithms=1 allowed_pairwise_ciphers=6 allowed_group_ciphers=15 hidden_ssid=false is_passpoint=false is_ephemeral=false has_ever_connected=true scan_rssi=-30 scan_freq=2447\n" +
            "07-22 14:51:17.010 CMD_ASSOCIATED_BSSID totalTxBytes=55006 totalRxBytes=28737 screenOn=true cellularData=false, supplicantStateChangeEvents: { AUTHENTICATING ASSOCIATING ASSOCIATED }\n" +
            "07-22 14:51:17.039 NETWORK_CONNECTION_EVENT totalTxBytes=55198 totalRxBytes=28777 screenOn=true cellularData=false\n" +
            "07-22 14:51:17.264 CMD_IP_CONFIGURATION_SUCCESSFUL lastRssi=-30 lastFreq=2447 lastLinkSpeed=1 lastScore=60 totalTxBytes=55422 totalRxBytes=31949 screenOn=true cellularData=false, supplicantStateChangeEvents: { COMPLETED }\n" +
            "07-22 14:51:18.525 NETWORK_AGENT_VALID_NETWORK totalTxBytes=64617 totalRxBytes=45731 screenOn=true cellularData=false\n" +
            "07-22 15:14:44.821 NETWORK_DISCONNECTION_EVENT local_gen=true reason=3:DEAUTH_LEAVING lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=394612 totalRxBytes=1536108 screenOn=true cellularData=false\n" +
            "07-22 15:14:44.880 WIFI_DISABLED totalTxBytes=394612 totalRxBytes=1536108 screenOn=true cellularData=false, supplicantStateChangeEvents: { DISCONNECTED }\n" +
            "07-22 15:14:53.758 WIFI_ENABLED totalTxBytes=412132 totalRxBytes=1544540 screenOn=true cellularData=false\n" +
            "07-22 15:14:57.522 CMD_START_CONNECT totalTxBytes=422712 totalRxBytes=1549734 screenOn=true cellularData=false, ConfigInfo: allowed_key_management=1 allowed_protocols=3 allowed_auth_algorithms=1 allowed_pairwise_ciphers=6 allowed_group_ciphers=15 hidden_ssid=false is_passpoint=false is_ephemeral=false has_ever_connected=true scan_rssi=-30 scan_freq=2447\n" +
            "07-22 15:14:57.539 CMD_ASSOCIATED_BSSID totalTxBytes=422712 totalRxBytes=1549734 screenOn=true cellularData=false, supplicantStateChangeEvents: { AUTHENTICATING ASSOCIATING ASSOCIATED }\n" +
            "07-22 15:14:57.549 NETWORK_CONNECTION_EVENT totalTxBytes=422904 totalRxBytes=1549774 screenOn=true cellularData=false\n" +
            "07-22 15:14:57.594 CMD_IP_CONFIGURATION_SUCCESSFUL lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=423052 totalRxBytes=1551396 screenOn=true cellularData=false, supplicantStateChangeEvents: { COMPLETED }\n" +
            "07-22 15:14:57.880 NETWORK_AGENT_VALID_NETWORK totalTxBytes=426173 totalRxBytes=1554789 screenOn=true cellularData=false\n" +
            "07-22 15:22:10.966 NETWORK_DISCONNECTION_EVENT local_gen=true reason=3:DEAUTH_LEAVING lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=473990 totalRxBytes=1598734 screenOn=true cellularData=false\n" +
            "07-22 15:22:10.990 WIFI_DISABLED totalTxBytes=473990 totalRxBytes=1598734 screenOn=true cellularData=false, supplicantStateChangeEvents: { DISCONNECTED }\n" +
            "07-22 15:23:36.441 WIFI_ENABLED totalTxBytes=501224 totalRxBytes=1612079 screenOn=true cellularData=false\n" +
            "07-22 15:23:40.229 CMD_START_CONNECT totalTxBytes=511804 totalRxBytes=1617273 screenOn=true cellularData=false, ConfigInfo: allowed_key_management=1 allowed_protocols=3 allowed_auth_algorithms=1 allowed_pairwise_ciphers=6 allowed_group_ciphers=15 hidden_ssid=false is_passpoint=false is_ephemeral=false has_ever_connected=true scan_rssi=-30 scan_freq=2447\n" +
            "07-22 15:23:40.247 CMD_ASSOCIATED_BSSID totalTxBytes=511804 totalRxBytes=1617273 screenOn=true cellularData=false, supplicantStateChangeEvents: { AUTHENTICATING ASSOCIATING ASSOCIATED }\n" +
            "07-22 15:23:40.258 NETWORK_CONNECTION_EVENT totalTxBytes=511996 totalRxBytes=1617313 screenOn=true cellularData=false\n" +
            "07-22 15:23:40.309 CMD_IP_CONFIGURATION_SUCCESSFUL lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=512144 totalRxBytes=1618935 screenOn=true cellularData=false, supplicantStateChangeEvents: { COMPLETED }\n" +
            "07-22 15:23:40.606 NETWORK_AGENT_VALID_NETWORK totalTxBytes=515197 totalRxBytes=1622121 screenOn=true cellularData=false\n" +
            "07-22 15:24:13.975 NETWORK_DISCONNECTION_EVENT local_gen=true reason=3:DEAUTH_LEAVING lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=553278 totalRxBytes=1657055 screenOn=true cellularData=false\n" +
            "07-22 15:24:13.999 WIFI_DISABLED totalTxBytes=553278 totalRxBytes=1657055 screenOn=true cellularData=false, supplicantStateChangeEvents: { DISCONNECTED }\n" +
            "UserActionEvents:\n" +
            "07-22 15:06:41.691 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1036317\n" +
            "07-22 15:08:20.884 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1135510\n" +
            "07-22 15:09:31.070 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1205696\n" +
            "07-22 15:14:37.980 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1512606\n" +
            "07-22 15:14:44.564 eventType=EVENT_TOGGLE_WIFI_OFF startTimeMillis=1519191\n" +
            "07-22 15:14:53.614 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1528240\n" +
            "07-22 15:22:10.767 eventType=EVENT_TOGGLE_WIFI_OFF startTimeMillis=1965394\n" +
            "07-22 15:23:36.305 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=2050931\n" +
            "07-22 15:24:05.395 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=2080021\n" +
            "mWifiLogProto.numPasspointProviders=0\n" +
            "mWifiLogProto.numPasspointProviderInstallation=0\n" +
            "mWifiLogProto.numPasspointProviderInstallSuccess=0\n" +
            "mWifiLogProto.numPasspointProviderUninstallation=0\n" +
            "mWifiLogProto.numPasspointProviderUninstallSuccess=0\n" +
            "mWifiLogProto.numPasspointProvidersSuccessfullyConnected=0\n" +
            "mWifiLogProto.installedPasspointProfileTypeForR1:{}\n" +
            "mWifiLogProto.installedPasspointProfileTypeForR2:{}\n" +
            "mWifiLogProto.passpointProvisionStats.numProvisionSuccess=0\n" +
            "mWifiLogProto.passpointProvisionStats.provisionFailureCount:{}\n" +
            "mWifiLogProto.numRadioModeChangeToMcc=0\n" +
            "mWifiLogProto.numRadioModeChangeToScc=0\n" +
            "mWifiLogProto.numRadioModeChangeToSbs=0\n" +
            "mWifiLogProto.numRadioModeChangeToDbs=0\n" +
            "mWifiLogProto.numSoftApUserBandPreferenceUnsatisfied=0\n" +
            "mTotalSsidsInScanHistogram:{1=5}\n" +
            "mTotalBssidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenSsidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenBssidsInScanHistogram:{1=5}\n" +
            "mAvailableSavedSsidsInScanHistogram:{1=5}\n" +
            "mAvailableSavedBssidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenOrSavedSsidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenOrSavedBssidsInScanHistogram:{1=5}\n" +
            "mAvailableSavedPasspointProviderProfilesInScanHistogram:{0=5}\n" +
            "mAvailableSavedPasspointProviderBssidsInScanHistogram:{0=5}\n" +
            "mWifiLogProto.partialAllSingleScanListenerResults=17\n" +
            "mWifiLogProto.fullBandAllSingleScanListenerResults=5\n" +
            "mWifiAwareMetrics:\n" +
            "mLastEnableUsageMs:0\n" +
            "mLastEnableUsageInThisSampleWindowMs:0\n" +
            "mAvailableTimeMs:0\n" +
            "mHistogramAwareAvailableDurationMs:\n" +
            "mLastEnableAwareMs:0\n" +
            "mLastEnableAwareInThisSampleWindowMs:0\n" +
            "mEnabledTimeMs:0\n" +
            "mHistogramAwareEnabledDurationMs:\n" +
            "mAttachDataByUid:\n" +
            "mAttachStatusData:\n" +
            "mHistogramAttachDuration:\n" +
            "mMaxPublishInApp:0\n" +
            "mMaxSubscribeInApp:0\n" +
            "mMaxDiscoveryInApp:0\n" +
            "mMaxPublishInSystem:0\n" +
            "mMaxSubscribeInSystem:0\n" +
            "mMaxDiscoveryInSystem:0\n" +
            "mPublishStatusData:\n" +
            "mSubscribeStatusData:\n" +
            "mHistogramPublishDuration:\n" +
            "mHistogramSubscribeDuration:\n" +
            "mAppsWithDiscoverySessionResourceFailure:\n" +
            "mMaxPublishWithRangingInApp:0\n" +
            "mMaxSubscribeWithRangingInApp:0\n" +
            "mMaxPublishWithRangingInSystem:0\n" +
            "mMaxSubscribeWithRangingInSystem:0\n" +
            "mHistogramSubscribeGeofenceMin:\n" +
            "mHistogramSubscribeGeofenceMax:\n" +
            "mNumSubscribesWithRanging:0\n" +
            "mNumMatchesWithRanging:0\n" +
            "mNumMatchesWithoutRangingForRangingEnabledSubscribes:0\n" +
            "mMaxNdiInApp:0\n" +
            "mMaxNdpInApp:0\n" +
            "mMaxSecureNdpInApp:0\n" +
            "mMaxNdiInSystem:0\n" +
            "mMaxNdpInSystem:0\n" +
            "mMaxSecureNdpInSystem:0\n" +
            "mMaxNdpPerNdi:0\n" +
            "mInBandNdpStatusData:\n" +
            "mOutOfBandNdpStatusData:\n" +
            "mNdpCreationTimeDuration:\n" +
            "mNdpCreationTimeMin:-1\n" +
            "mNdpCreationTimeMax:0\n" +
            "mNdpCreationTimeSum:0\n" +
            "mNdpCreationTimeSumSq:0\n" +
            "mNdpCreationTimeNumSamples:0\n" +
            "mHistogramNdpDuration:\n" +
            "mRttMetrics:\n" +
            "RTT Metrics:\n" +
            "mNumStartRangingCalls:0\n" +
            "mOverallStatusHistogram:{}\n" +
            "mMeasurementDurationApOnlyHistogram{}\n" +
            "mMeasurementDurationWithAwareHistogram{}\n" +
            "AP:numCalls=0, numIndividualCalls=0, perUidInfo={}, numRequestsHistogram={}, requestGapHistogram={}, statusHistogram={}, measuredDistanceHistogram={}\n" +
            "AWARE:numCalls=0, numIndividualCalls=0, perUidInfo={}, numRequestsHistogram={}, requestGapHistogram={}, statusHistogram={}, measuredDistanceHistogram={}\n" +
            "mPnoScanMetrics.numPnoScanAttempts=0\n" +
            "mPnoScanMetrics.numPnoScanFailed=0\n" +
            "mPnoScanMetrics.numPnoScanStartedOverOffload=0\n" +
            "mPnoScanMetrics.numPnoScanFailedOverOffload=0\n" +
            "mPnoScanMetrics.numPnoFoundNetworkEvents=0\n" +
            "mWifiLinkLayerUsageStats.loggingDurationMs=1976352\n" +
            "mWifiLinkLayerUsageStats.radioOnTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioTxTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioRxTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioNanScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioBackgroundScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioRoamScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioPnoScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioHs20ScanTimeMs=0\n" +
            "mWifiLogProto.connectToNetworkNotificationCount={}\n" +
            "mWifiLogProto.connectToNetworkNotificationActionCount={}\n" +
            "mWifiLogProto.openNetworkRecommenderBlacklistSize=0\n" +
            "mWifiLogProto.isWifiNetworksAvailableNotificationOn=true\n" +
            "mWifiLogProto.numOpenNetworkRecommendationUpdates=0\n" +
            "mWifiLogProto.numOpenNetworkConnectMessageFailedToSend=0\n" +
            "mWifiLogProto.observedHotspotR1ApInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR2ApInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR3ApInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR1EssInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR2EssInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR3EssInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR1ApsPerEssInScanHistogram={}\n" +
            "mWifiLogProto.observedHotspotR2ApsPerEssInScanHistogram={}\n" +
            "mWifiLogProto.observedHotspotR3ApsPerEssInScanHistogram={}\n" +
            "mWifiLogProto.observed80211mcSupportingApsInScanHistogram{0=5}\n" +
            "mWifiLogProto.bssidBlocklistStats:\n" +
            "networkSelectionFilteredBssidCount={0=21}, highMovementMultipleScansFeatureEnabled=true, numHighMovementConnectionSkipped=0, numHighMovementConnectionStarted=0\n" +
            "mSoftApTetheredEvents:\n" +
            "mSoftApLocalOnlyEvents:\n" +
            "Wifi power metrics:\n" +
            "Logging duration (time on battery): 258781043\n" +
            "Energy consumed by wifi (mAh): 0.0\n" +
            "Amount of time wifi is in idle (ms): 0\n" +
            "Amount of time wifi is in rx (ms): 0\n" +
            "Amount of time wifi is in tx (ms): 0\n" +
            "Amount of time kernel is active because of wifi data (ms): 437834\n" +
            "Amount of time wifi is in sleep (ms): 258781043\n" +
            "Amount of time wifi is scanning (ms): 0\n" +
            "Number of packets sent (tx): 0\n" +
            "Number of bytes sent (tx): 0\n" +
            "Number of packets received (rx): 0\n" +
            "Number of bytes sent (rx): 0\n" +
            "Energy consumed across measured wifi rails (mAh): 0\n" +
            "Wifi radio usage metrics:\n" +
            "Logging duration (time on battery): 258781044\n" +
            "Amount of time wifi is in scan mode while on battery (ms): 0\n" +
            "-------WifiWake metrics-------\n" +
            "mTotalSessions: 2\n" +
            "mTotalWakeups: 0\n" +
            "mIgnoredStarts: 0\n" +
            "mIsInSession: true\n" +
            "Stored Sessions: 2\n" +
            "WifiWakeMetrics.Session:\n" +
            "mStartTimestamp: 1519503\n" +
            "mStartNetworks: 1\n" +
            "mInitializeNetworks: 0\n" +
            "mInitEvent: {}\n" +
            "mUnlockEvent: {}\n" +
            "mWakeupEvent: {}\n" +
            "mResetEvent: { mNumScans: 1, elapsedTime: 8879 }\n" +
            "WifiWakeMetrics.Session:\n" +
            "mStartTimestamp: 1965614\n" +
            "mStartNetworks: 1\n" +
            "mInitializeNetworks: 0\n" +
            "mInitEvent: {}\n" +
            "mUnlockEvent: {}\n" +
            "mWakeupEvent: {}\n" +
            "mResetEvent: { mNumScans: 1, elapsedTime: 85450 }\n" +
            "Current Session: \n" +
            "WifiWakeMetrics.Session:\n" +
            "mStartTimestamp: 2088622\n" +
            "mStartNetworks: 1\n" +
            "mInitializeNetworks: 0\n" +
            "mInitEvent: {}\n" +
            "mUnlockEvent: {}\n" +
            "mWakeupEvent: {}\n" +
            "mResetEvent: {}\n" +
            "----end of WifiWake metrics----\n" +
            "mWifiLogProto.isMacRandomizationOn=false\n" +
            "mWifiLogProto.scoreExperimentId=\n" +
            "mExperimentValues.wifiIsUnusableLoggingEnabled=true\n" +
            "mExperimentValues.wifiDataStallMinTxBad=1\n" +
            "mExperimentValues.wifiDataStallMinTxSuccessWithoutRx=50\n" +
            "mExperimentValues.linkSpeedCountsLoggingEnabled=true\n" +
            "mExperimentValues.dataStallDurationMs=1500\n" +
            "mExperimentValues.dataStallTxTputThrKbps=2000\n" +
            "mExperimentValues.dataStallRxTputThrKbps=2000\n" +
            "mExperimentValues.dataStallTxPerThr=90\n" +
            "mExperimentValues.dataStallCcaLevelThr=256\n" +
            "WifiIsUnusableEventList: \n" +
            "Hardware Version: \n" +
            "mWifiUsabilityStatsEntriesList:\n" +
            "timestamp_ms=1929963,rssi=-30,link_speed_mbps=52,total_tx_success=1363,total_tx_retries=0,total_tx_bad=0,total_rx_success=1492,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=600,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "timestamp_ms=2088024,rssi=-30,link_speed_mbps=52,total_tx_success=1430,total_tx_retries=0,total_tx_bad=0,total_rx_success=1546,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=623,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "mWifiUsabilityStatsList:\n" +
            "\n" +
            "label=1\n" +
            "\n" +
            "trigger_type=0\n" +
            "\n" +
            "time_stamp_ms=409751\n" +
            "timestamp_ms=292338,rssi=-30,link_speed_mbps=52,total_tx_success=1218,total_tx_retries=0,total_tx_bad=0,total_rx_success=1353,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=60,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "timestamp_ms=295351,rssi=-30,link_speed_mbps=52,total_tx_success=1218,total_tx_retries=0,total_tx_bad=0,total_rx_success=1353,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=61,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "timestamp_ms=409748,rssi=-30,link_speed_mbps=52,total_tx_success=1256,total_tx_retries=0,total_tx_bad=0,total_rx_success=1398,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=99,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "mMobilityStatePnoStatsMap:\n" +
            "device_mobility_state=0,num_times_entered_state=1,total_duration_ms=0,pno_duration_ms=0\n" +
            "WifiP2pMetrics:\n" +
            "mConnectionEvents:\n" +
            "mGroupEvents:\n" +
            "mWifiP2pStatsProto.numPersistentGroup=0\n" +
            "mWifiP2pStatsProto.numTotalPeerScans=0\n" +
            "mWifiP2pStatsProto.numTotalServiceScans=0\n" +
            "mDppMetrics:\n" +
            "---Easy Connect/DPP metrics---\n" +
            "mWifiDppLogProto.numDppConfiguratorInitiatorRequests=0\n" +
            "mWifiDppLogProto.numDppEnrolleeInitiatorRequests=0\n" +
            "mWifiDppLogProto.numDppEnrolleeSuccess=0\n" +
            "mWifiDppLogProto.numDppR1CapableEnrolleeResponderDevices=0\n" +
            "mWifiDppLogProto.numDppR2CapableEnrolleeResponderDevices=0\n" +
            "mWifiDppLogProto.numDppR2EnrolleeResponderIncompatibleConfiguration=0\n" +
            "---End of Easy Connect/DPP metrics---\n" +
            "mWifiConfigStoreReadDurationHistogram:{1=2}\n" +
            "mWifiConfigStoreWriteDurationHistogram:{0=7, 5=2}\n" +
            "mLinkProbeSuccessRssiCounts:{}\n" +
            "mLinkProbeFailureRssiCounts:{}\n" +
            "mLinkProbeSuccessLinkSpeedCounts:{}\n" +
            "mLinkProbeFailureLinkSpeedCounts:{}\n" +
            "mLinkProbeSuccessSecondsSinceLastTxSuccessHistogram:{}\n" +
            "mLinkProbeFailureSecondsSinceLastTxSuccessHistogram:{}\n" +
            "mLinkProbeSuccessElapsedTimeMsHistogram:{}\n" +
            "mLinkProbeFailureReasonCounts:{}\n" +
            "mLinkProbeExperimentProbeCounts:{}\n" +
            "mNetworkSelectionExperimentPairNumChoicesCounts:{Pair{42902385 42330058}=NetworkSelectionExperimentResults{sameSelectionNumChoicesCounter={1=21}, differentSelectionNumChoicesCounter={}}, Pair{42598152 42330058}=NetworkSelectionExperimentResults{sameSelectionNumChoicesCounter={1=21}, differentSelectionNumChoicesCounter={}}, Pair{42504592 42330058}=NetworkSelectionExperimentResults{sameSelectionNumChoicesCounter={1=21}, differentSelectionNumChoicesCounter={}}}\n" +
            "mLinkProbeStaEventCount:0\n" +
            "mWifiNetworkRequestApiLog:\n" +
            "num_apps: 0\n" +
            "num_connect_success: 0\n" +
            "num_request: 0\n" +
            "num_user_approval_bypass: 0\n" +
            "num_user_reject: 0\n" +
            "\n" +
            "mWifiNetworkRequestApiMatchSizeHistogram:\n" +
            "{}\n" +
            "mWifiNetworkSuggestionApiLog:\n" +
            "num_connect_failure: 0\n" +
            "num_connect_success: 0\n" +
            "num_modification: 0\n" +
            "user_revoke_app_suggestion_permission: 0\n" +
            "\n" +
            "mWifiNetworkSuggestionApiMatchSizeHistogram:\n" +
            "{}\n" +
            "mWifiNetworkSuggestionApiAppTypeCounter:\n" +
            "{}\n" +
            "mUserApprovalSuggestionAppUiUserReaction:\n" +
            "mUserApprovalCarrierUiUserReaction:\n" +
            "mNetworkIdToNominatorId:\n" +
            "{0=2}\n" +
            "mWifiLockStats:\n" +
            "high_perf_active_time_ms: 0\n" +
            "low_latency_active_time_ms: 0\n" +
            "\n" +
            "mWifiLockHighPerfAcqDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiLockLowLatencyAcqDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiLockHighPerfActiveSessionDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiLockLowLatencyActiveSessionDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiToggleStats:\n" +
            "num_toggle_off_normal: 0\n" +
            "num_toggle_off_privileged: 3\n" +
            "num_toggle_on_normal: 0\n" +
            "num_toggle_on_privileged: 7\n" +
            "\n" +
            "mWifiLogProto.numAddOrUpdateNetworkCalls=0\n" +
            "mWifiLogProto.numEnableNetworkCalls=0\n" +
            "mWifiLogProto.txLinkSpeedCount2g={1=1, 45=1, 52=622}\n" +
            "mWifiLogProto.txLinkSpeedCount5gLow={}\n" +
            "mWifiLogProto.txLinkSpeedCount5gMid={}\n" +
            "mWifiLogProto.txLinkSpeedCount5gHigh={}\n" +
            "mWifiLogProto.txLinkSpeedCount6gLow={}\n" +
            "mWifiLogProto.txLinkSpeedCount6gMid={}\n" +
            "mWifiLogProto.txLinkSpeedCount6gHigh={}\n" +
            "mWifiLogProto.rxLinkSpeedCount2g={52=621}\n" +
            "mWifiLogProto.rxLinkSpeedCount5gLow={}\n" +
            "mWifiLogProto.rxLinkSpeedCount5gMid={}\n" +
            "mWifiLogProto.rxLinkSpeedCount5gHigh={}\n" +
            "mWifiLogProto.rxLinkSpeedCount6gLow={}\n" +
            "mWifiLogProto.rxLinkSpeedCount6gMid={}\n" +
            "mWifiLogProto.rxLinkSpeedCount6gHigh={}\n" +
            "mWifiLogProto.numIpRenewalFailure=0\n" +
            "mWifiLogProto.connectionDurationStats=connectionDurationSufficientThroughputMs=0, connectionDurationInSufficientThroughputMs=0, connectionDurationCellularDataOffMs=1870500\n" +
            "mWifiLogProto.isExternalWifiScorerOn=false\n" +
            "mWifiLogProto.wifiOffMetrics=numWifiOff=3, numWifiOffDeferring=0, numWifiOffDeferringTimeout=0, wifiOffDeferringTimeHistogram={}\n" +
            "mWifiLogProto.softApConfigLimitationMetrics=numSecurityTypeResetToDefault=0, numMaxClientSettingResetToDefault=0, numClientControlByUserResetToDefault=0, maxClientSettingWhenReachHistogram={}\n" +
            "mChannelUtilizationHistogram2G:\n" +
            "{[75,100)=624}\n" +
            "mChannelUtilizationHistogramAbove2G:\n" +
            "{}\n" +
            "mTxThroughputMbpsHistogram2G:\n" +
            "{[25,50)=621, [100,150)=3}\n" +
            "mRxThroughputMbpsHistogram2G:\n" +
            "{[25,50)=621, [100,150)=3}\n" +
            "mTxThroughputMbpsHistogramAbove2G:\n" +
            "{}\n" +
            "mRxThroughputMbpsHistogramAbove2G:\n" +
            "{}\n" +
            "mCarrierWifiMetrics:\n" +
            "numConnectionSuccess=0, numConnectionAuthFailure=0, numConnectionNonAuthFailure0\n" +
            "mInitPartialScanTotalCount:\n" +
            "0\n" +
            "mInitPartialScanSuccessCount:\n" +
            "0\n" +
            "mInitPartialScanFailureCount:\n" +
            "0\n" +
            "mInitPartialScanSuccessHistogram:\n" +
            "{}\n" +
            "mInitPartialScanFailureHistogram:\n" +
            "{}\n" +
            "\n" +
            "Dump of WifiNetworkSuggestionsManager\n" +
            "WifiNetworkSuggestionsManager - Networks Begin ----\n" +
            "WifiNetworkSuggestionsManager - Networks End ----\n" +
            "WifiNetworkSuggestionsManager - Network Suggestions matching connection: null\n" +
            "\n" +
            "Dump of WifiBackupRestore\n" +
            "\n" +
            "ScoringParams: rssi2=-83:-80:-73:-60,rssi5=-80:-77:-70:-57,rssi6=-80:-77:-70:-57,pps=0:16:100,horizon=15,nud=8,expid=0\n" +
            "\n" +
            "WifiScoreReport:\n" +
            "time,session,netid,rssi,filtered_rssi,rssi_threshold,freq,txLinkSpeed,rxLinkSpeed,tx_good,tx_retry,tx_bad,rx_pps,nudrq,nuds,s1,s2,score\n" +
            "07-22 14:51:17.049,0,100,-30.0,-30.0,-83.0,2447,1,-1,0.00,0.00,0.00,0.63,0,1,93,103,60\n" +
            "07-22 15:24:13.400,7,102,-30.0,-30.0,-83.0,2447,52,52,0.64,0.00,0.00,0.42,0,3,93,103,60\n" +
            "\n" +
            "Dump of SarManager\n" +
            "isSarSupported: false\n" +
            "isSarVoiceCallSupported: false\n" +
            "isSarSoftApSupported: false\n" +
            "\n" +
            "\n" +
            "WifiNetworkScoreCache (android/1000)\n" +
            "  All score curves:\n" +
            "  Network scores for latest ScanResults:\n" +
            "    \"AndroidWifi\"02:15:b2:00:01:00: -128\n" +
            "\n" +
            "Dump of WifiSettingsConfigStore\n" +
            "Settings:\n" +
            "wifi_p2p_pending_factory_reset=false\n" +
            "wifi_scan_throttle_enabled=true\n" +
            "wifi_p2p_device_name=null\n" +
            "wifi_scan_always_enabled=true\n" +
            "wifi_verbose_logging_enabled=false\n" +
            "\n";

    public static final String DUMP_API30_DISCONNECTED = "Wi-Fi is disabled\n" +
            "Verbose logging is off\n" +
            "Stay-awake conditions: 1\n" +
            "mInIdleMode false\n" +
            "mScanPending false\n" +
            "mPersistWifiState 0\n" +
            "StaEventList:\n" +
            "07-22 14:51:08.614 WIFI_ENABLED totalTxBytes=51768 totalRxBytes=27118 screenOn=true cellularData=false\n" +
            "07-22 14:51:16.967 CMD_START_CONNECT totalTxBytes=55006 totalRxBytes=28737 screenOn=true cellularData=false, ConfigInfo: allowed_key_management=1 allowed_protocols=3 allowed_auth_algorithms=1 allowed_pairwise_ciphers=6 allowed_group_ciphers=15 hidden_ssid=false is_passpoint=false is_ephemeral=false has_ever_connected=true scan_rssi=-30 scan_freq=2447\n" +
            "07-22 14:51:17.010 CMD_ASSOCIATED_BSSID totalTxBytes=55006 totalRxBytes=28737 screenOn=true cellularData=false, supplicantStateChangeEvents: { AUTHENTICATING ASSOCIATING ASSOCIATED }\n" +
            "07-22 14:51:17.039 NETWORK_CONNECTION_EVENT totalTxBytes=55198 totalRxBytes=28777 screenOn=true cellularData=false\n" +
            "07-22 14:51:17.264 CMD_IP_CONFIGURATION_SUCCESSFUL lastRssi=-30 lastFreq=2447 lastLinkSpeed=1 lastScore=60 totalTxBytes=55422 totalRxBytes=31949 screenOn=true cellularData=false, supplicantStateChangeEvents: { COMPLETED }\n" +
            "07-22 14:51:18.525 NETWORK_AGENT_VALID_NETWORK totalTxBytes=64617 totalRxBytes=45731 screenOn=true cellularData=false\n" +
            "07-22 15:14:44.821 NETWORK_DISCONNECTION_EVENT local_gen=true reason=3:DEAUTH_LEAVING lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=394612 totalRxBytes=1536108 screenOn=true cellularData=false\n" +
            "07-22 15:14:44.880 WIFI_DISABLED totalTxBytes=394612 totalRxBytes=1536108 screenOn=true cellularData=false, supplicantStateChangeEvents: { DISCONNECTED }\n" +
            "07-22 15:14:53.758 WIFI_ENABLED totalTxBytes=412132 totalRxBytes=1544540 screenOn=true cellularData=false\n" +
            "07-22 15:14:57.522 CMD_START_CONNECT totalTxBytes=422712 totalRxBytes=1549734 screenOn=true cellularData=false, ConfigInfo: allowed_key_management=1 allowed_protocols=3 allowed_auth_algorithms=1 allowed_pairwise_ciphers=6 allowed_group_ciphers=15 hidden_ssid=false is_passpoint=false is_ephemeral=false has_ever_connected=true scan_rssi=-30 scan_freq=2447\n" +
            "07-22 15:14:57.539 CMD_ASSOCIATED_BSSID totalTxBytes=422712 totalRxBytes=1549734 screenOn=true cellularData=false, supplicantStateChangeEvents: { AUTHENTICATING ASSOCIATING ASSOCIATED }\n" +
            "07-22 15:14:57.549 NETWORK_CONNECTION_EVENT totalTxBytes=422904 totalRxBytes=1549774 screenOn=true cellularData=false\n" +
            "07-22 15:14:57.594 CMD_IP_CONFIGURATION_SUCCESSFUL lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=423052 totalRxBytes=1551396 screenOn=true cellularData=false, supplicantStateChangeEvents: { COMPLETED }\n" +
            "07-22 15:14:57.880 NETWORK_AGENT_VALID_NETWORK totalTxBytes=426173 totalRxBytes=1554789 screenOn=true cellularData=false\n" +
            "07-22 15:22:10.966 NETWORK_DISCONNECTION_EVENT local_gen=true reason=3:DEAUTH_LEAVING lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=473990 totalRxBytes=1598734 screenOn=true cellularData=false\n" +
            "07-22 15:22:10.990 WIFI_DISABLED totalTxBytes=473990 totalRxBytes=1598734 screenOn=true cellularData=false, supplicantStateChangeEvents: { DISCONNECTED }\n" +
            "07-22 15:23:36.441 WIFI_ENABLED totalTxBytes=501224 totalRxBytes=1612079 screenOn=true cellularData=false\n" +
            "07-22 15:23:40.229 CMD_START_CONNECT totalTxBytes=511804 totalRxBytes=1617273 screenOn=true cellularData=false, ConfigInfo: allowed_key_management=1 allowed_protocols=3 allowed_auth_algorithms=1 allowed_pairwise_ciphers=6 allowed_group_ciphers=15 hidden_ssid=false is_passpoint=false is_ephemeral=false has_ever_connected=true scan_rssi=-30 scan_freq=2447\n" +
            "07-22 15:23:40.247 CMD_ASSOCIATED_BSSID totalTxBytes=511804 totalRxBytes=1617273 screenOn=true cellularData=false, supplicantStateChangeEvents: { AUTHENTICATING ASSOCIATING ASSOCIATED }\n" +
            "07-22 15:23:40.258 NETWORK_CONNECTION_EVENT totalTxBytes=511996 totalRxBytes=1617313 screenOn=true cellularData=false\n" +
            "07-22 15:23:40.309 CMD_IP_CONFIGURATION_SUCCESSFUL lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=512144 totalRxBytes=1618935 screenOn=true cellularData=false, supplicantStateChangeEvents: { COMPLETED }\n" +
            "07-22 15:23:40.606 NETWORK_AGENT_VALID_NETWORK totalTxBytes=515197 totalRxBytes=1622121 screenOn=true cellularData=false\n" +
            "07-22 15:24:13.975 NETWORK_DISCONNECTION_EVENT local_gen=true reason=3:DEAUTH_LEAVING lastRssi=-30 lastFreq=2447 lastLinkSpeed=52 lastScore=60 totalTxBytes=553278 totalRxBytes=1657055 screenOn=true cellularData=false\n" +
            "07-22 15:24:13.999 WIFI_DISABLED totalTxBytes=553278 totalRxBytes=1657055 screenOn=true cellularData=false, supplicantStateChangeEvents: { DISCONNECTED }\n" +
            "UserActionEvents:\n" +
            "07-22 15:06:41.691 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1036317\n" +
            "07-22 15:08:20.884 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1135510\n" +
            "07-22 15:09:31.070 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1205696\n" +
            "07-22 15:14:37.980 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1512606\n" +
            "07-22 15:14:44.564 eventType=EVENT_TOGGLE_WIFI_OFF startTimeMillis=1519191\n" +
            "07-22 15:14:53.614 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=1528240\n" +
            "07-22 15:22:10.767 eventType=EVENT_TOGGLE_WIFI_OFF startTimeMillis=1965394\n" +
            "07-22 15:23:36.305 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=2050931\n" +
            "07-22 15:24:05.395 eventType=EVENT_TOGGLE_WIFI_ON startTimeMillis=2080021\n" +
            "07-22 15:24:13.743 eventType=EVENT_TOGGLE_WIFI_OFF startTimeMillis=2088369\n" +
            "mWifiLogProto.numPasspointProviders=0\n" +
            "mWifiLogProto.numPasspointProviderInstallation=0\n" +
            "mWifiLogProto.numPasspointProviderInstallSuccess=0\n" +
            "mWifiLogProto.numPasspointProviderUninstallation=0\n" +
            "mWifiLogProto.numPasspointProviderUninstallSuccess=0\n" +
            "mWifiLogProto.numPasspointProvidersSuccessfullyConnected=0\n" +
            "mWifiLogProto.installedPasspointProfileTypeForR1:{}\n" +
            "mWifiLogProto.installedPasspointProfileTypeForR2:{}\n" +
            "mWifiLogProto.passpointProvisionStats.numProvisionSuccess=0\n" +
            "mWifiLogProto.passpointProvisionStats.provisionFailureCount:{}\n" +
            "mWifiLogProto.numRadioModeChangeToMcc=0\n" +
            "mWifiLogProto.numRadioModeChangeToScc=0\n" +
            "mWifiLogProto.numRadioModeChangeToSbs=0\n" +
            "mWifiLogProto.numRadioModeChangeToDbs=0\n" +
            "mWifiLogProto.numSoftApUserBandPreferenceUnsatisfied=0\n" +
            "mTotalSsidsInScanHistogram:{1=5}\n" +
            "mTotalBssidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenSsidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenBssidsInScanHistogram:{1=5}\n" +
            "mAvailableSavedSsidsInScanHistogram:{1=5}\n" +
            "mAvailableSavedBssidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenOrSavedSsidsInScanHistogram:{1=5}\n" +
            "mAvailableOpenOrSavedBssidsInScanHistogram:{1=5}\n" +
            "mAvailableSavedPasspointProviderProfilesInScanHistogram:{0=5}\n" +
            "mAvailableSavedPasspointProviderBssidsInScanHistogram:{0=5}\n" +
            "mWifiLogProto.partialAllSingleScanListenerResults=17\n" +
            "mWifiLogProto.fullBandAllSingleScanListenerResults=5\n" +
            "mWifiAwareMetrics:\n" +
            "mLastEnableUsageMs:0\n" +
            "mLastEnableUsageInThisSampleWindowMs:0\n" +
            "mAvailableTimeMs:0\n" +
            "mHistogramAwareAvailableDurationMs:\n" +
            "mLastEnableAwareMs:0\n" +
            "mLastEnableAwareInThisSampleWindowMs:0\n" +
            "mEnabledTimeMs:0\n" +
            "mHistogramAwareEnabledDurationMs:\n" +
            "mAttachDataByUid:\n" +
            "mAttachStatusData:\n" +
            "mHistogramAttachDuration:\n" +
            "mMaxPublishInApp:0\n" +
            "mMaxSubscribeInApp:0\n" +
            "mMaxDiscoveryInApp:0\n" +
            "mMaxPublishInSystem:0\n" +
            "mMaxSubscribeInSystem:0\n" +
            "mMaxDiscoveryInSystem:0\n" +
            "mPublishStatusData:\n" +
            "mSubscribeStatusData:\n" +
            "mHistogramPublishDuration:\n" +
            "mHistogramSubscribeDuration:\n" +
            "mAppsWithDiscoverySessionResourceFailure:\n" +
            "mMaxPublishWithRangingInApp:0\n" +
            "mMaxSubscribeWithRangingInApp:0\n" +
            "mMaxPublishWithRangingInSystem:0\n" +
            "mMaxSubscribeWithRangingInSystem:0\n" +
            "mHistogramSubscribeGeofenceMin:\n" +
            "mHistogramSubscribeGeofenceMax:\n" +
            "mNumSubscribesWithRanging:0\n" +
            "mNumMatchesWithRanging:0\n" +
            "mNumMatchesWithoutRangingForRangingEnabledSubscribes:0\n" +
            "mMaxNdiInApp:0\n" +
            "mMaxNdpInApp:0\n" +
            "mMaxSecureNdpInApp:0\n" +
            "mMaxNdiInSystem:0\n" +
            "mMaxNdpInSystem:0\n" +
            "mMaxSecureNdpInSystem:0\n" +
            "mMaxNdpPerNdi:0\n" +
            "mInBandNdpStatusData:\n" +
            "mOutOfBandNdpStatusData:\n" +
            "mNdpCreationTimeDuration:\n" +
            "mNdpCreationTimeMin:-1\n" +
            "mNdpCreationTimeMax:0\n" +
            "mNdpCreationTimeSum:0\n" +
            "mNdpCreationTimeSumSq:0\n" +
            "mNdpCreationTimeNumSamples:0\n" +
            "mHistogramNdpDuration:\n" +
            "mRttMetrics:\n" +
            "RTT Metrics:\n" +
            "mNumStartRangingCalls:0\n" +
            "mOverallStatusHistogram:{}\n" +
            "mMeasurementDurationApOnlyHistogram{}\n" +
            "mMeasurementDurationWithAwareHistogram{}\n" +
            "AP:numCalls=0, numIndividualCalls=0, perUidInfo={}, numRequestsHistogram={}, requestGapHistogram={}, statusHistogram={}, measuredDistanceHistogram={}\n" +
            "AWARE:numCalls=0, numIndividualCalls=0, perUidInfo={}, numRequestsHistogram={}, requestGapHistogram={}, statusHistogram={}, measuredDistanceHistogram={}\n" +
            "mPnoScanMetrics.numPnoScanAttempts=0\n" +
            "mPnoScanMetrics.numPnoScanFailed=0\n" +
            "mPnoScanMetrics.numPnoScanStartedOverOffload=0\n" +
            "mPnoScanMetrics.numPnoScanFailedOverOffload=0\n" +
            "mPnoScanMetrics.numPnoFoundNetworkEvents=0\n" +
            "mWifiLinkLayerUsageStats.loggingDurationMs=1976352\n" +
            "mWifiLinkLayerUsageStats.radioOnTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioTxTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioRxTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioNanScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioBackgroundScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioRoamScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioPnoScanTimeMs=0\n" +
            "mWifiLinkLayerUsageStats.radioHs20ScanTimeMs=0\n" +
            "mWifiLogProto.connectToNetworkNotificationCount={}\n" +
            "mWifiLogProto.connectToNetworkNotificationActionCount={}\n" +
            "mWifiLogProto.openNetworkRecommenderBlacklistSize=0\n" +
            "mWifiLogProto.isWifiNetworksAvailableNotificationOn=true\n" +
            "mWifiLogProto.numOpenNetworkRecommendationUpdates=0\n" +
            "mWifiLogProto.numOpenNetworkConnectMessageFailedToSend=0\n" +
            "mWifiLogProto.observedHotspotR1ApInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR2ApInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR3ApInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR1EssInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR2EssInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR3EssInScanHistogram={0=5}\n" +
            "mWifiLogProto.observedHotspotR1ApsPerEssInScanHistogram={}\n" +
            "mWifiLogProto.observedHotspotR2ApsPerEssInScanHistogram={}\n" +
            "mWifiLogProto.observedHotspotR3ApsPerEssInScanHistogram={}\n" +
            "mWifiLogProto.observed80211mcSupportingApsInScanHistogram{0=5}\n" +
            "mWifiLogProto.bssidBlocklistStats:\n" +
            "networkSelectionFilteredBssidCount={0=21}, highMovementMultipleScansFeatureEnabled=true, numHighMovementConnectionSkipped=0, numHighMovementConnectionStarted=0\n" +
            "mSoftApTetheredEvents:\n" +
            "mSoftApLocalOnlyEvents:\n" +
            "Wifi power metrics:\n" +
            "Logging duration (time on battery): 258781043\n" +
            "Energy consumed by wifi (mAh): 0.0\n" +
            "Amount of time wifi is in idle (ms): 0\n" +
            "Amount of time wifi is in rx (ms): 0\n" +
            "Amount of time wifi is in tx (ms): 0\n" +
            "Amount of time kernel is active because of wifi data (ms): 437834\n" +
            "Amount of time wifi is in sleep (ms): 258781043\n" +
            "Amount of time wifi is scanning (ms): 0\n" +
            "Number of packets sent (tx): 0\n" +
            "Number of bytes sent (tx): 0\n" +
            "Number of packets received (rx): 0\n" +
            "Number of bytes sent (rx): 0\n" +
            "Energy consumed across measured wifi rails (mAh): 0\n" +
            "Wifi radio usage metrics:\n" +
            "Logging duration (time on battery): 258781044\n" +
            "Amount of time wifi is in scan mode while on battery (ms): 0\n" +
            "-------WifiWake metrics-------\n" +
            "mTotalSessions: 2\n" +
            "mTotalWakeups: 0\n" +
            "mIgnoredStarts: 0\n" +
            "mIsInSession: true\n" +
            "Stored Sessions: 2\n" +
            "WifiWakeMetrics.Session:\n" +
            "mStartTimestamp: 1519503\n" +
            "mStartNetworks: 1\n" +
            "mInitializeNetworks: 0\n" +
            "mInitEvent: {}\n" +
            "mUnlockEvent: {}\n" +
            "mWakeupEvent: {}\n" +
            "mResetEvent: { mNumScans: 1, elapsedTime: 8879 }\n" +
            "WifiWakeMetrics.Session:\n" +
            "mStartTimestamp: 1965614\n" +
            "mStartNetworks: 1\n" +
            "mInitializeNetworks: 0\n" +
            "mInitEvent: {}\n" +
            "mUnlockEvent: {}\n" +
            "mWakeupEvent: {}\n" +
            "mResetEvent: { mNumScans: 1, elapsedTime: 85450 }\n" +
            "Current Session: \n" +
            "WifiWakeMetrics.Session:\n" +
            "mStartTimestamp: 2088622\n" +
            "mStartNetworks: 1\n" +
            "mInitializeNetworks: 0\n" +
            "mInitEvent: {}\n" +
            "mUnlockEvent: {}\n" +
            "mWakeupEvent: {}\n" +
            "mResetEvent: {}\n" +
            "----end of WifiWake metrics----\n" +
            "mWifiLogProto.isMacRandomizationOn=false\n" +
            "mWifiLogProto.scoreExperimentId=\n" +
            "mExperimentValues.wifiIsUnusableLoggingEnabled=true\n" +
            "mExperimentValues.wifiDataStallMinTxBad=1\n" +
            "mExperimentValues.wifiDataStallMinTxSuccessWithoutRx=50\n" +
            "mExperimentValues.linkSpeedCountsLoggingEnabled=true\n" +
            "mExperimentValues.dataStallDurationMs=1500\n" +
            "mExperimentValues.dataStallTxTputThrKbps=2000\n" +
            "mExperimentValues.dataStallRxTputThrKbps=2000\n" +
            "mExperimentValues.dataStallTxPerThr=90\n" +
            "mExperimentValues.dataStallCcaLevelThr=256\n" +
            "WifiIsUnusableEventList: \n" +
            "Hardware Version: \n" +
            "mWifiUsabilityStatsEntriesList:\n" +
            "timestamp_ms=1929963,rssi=-30,link_speed_mbps=52,total_tx_success=1363,total_tx_retries=0,total_tx_bad=0,total_rx_success=1492,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=600,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "timestamp_ms=2088024,rssi=-30,link_speed_mbps=52,total_tx_success=1430,total_tx_retries=0,total_tx_bad=0,total_rx_success=1546,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=623,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "mWifiUsabilityStatsList:\n" +
            "\n" +
            "label=1\n" +
            "\n" +
            "trigger_type=0\n" +
            "\n" +
            "time_stamp_ms=409751\n" +
            "timestamp_ms=292338,rssi=-30,link_speed_mbps=52,total_tx_success=1218,total_tx_retries=0,total_tx_bad=0,total_rx_success=1353,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=60,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "timestamp_ms=295351,rssi=-30,link_speed_mbps=52,total_tx_success=1218,total_tx_retries=0,total_tx_bad=0,total_rx_success=1353,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=61,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "timestamp_ms=409748,rssi=-30,link_speed_mbps=52,total_tx_success=1256,total_tx_retries=0,total_tx_bad=0,total_rx_success=1398,total_radio_on_time_ms=0,total_radio_tx_time_ms=0,total_radio_rx_time_ms=0,total_scan_time_ms=0,total_nan_scan_time_ms=0,total_background_scan_time_ms=0,total_roam_scan_time_ms=0,total_pno_scan_time_ms=0,total_hotspot_2_scan_time_ms=0,wifi_score=60,wifi_usability_score=-1,seq_num_to_framework=-1,prediction_horizon_sec=-1,total_cca_busy_freq_time_ms=0,total_radio_on_freq_time_ms=0,total_beacon_rx=0,probe_status_since_last_update=1,probe_elapsed_time_ms_since_last_update=-1,probe_mcs_rate_since_last_update=-1,rx_link_speed_mbps=52,seq_num_inside_framework=99,is_same_bssid_and_freq=true,device_mobility_state=0\n" +
            "mMobilityStatePnoStatsMap:\n" +
            "device_mobility_state=0,num_times_entered_state=1,total_duration_ms=0,pno_duration_ms=0\n" +
            "WifiP2pMetrics:\n" +
            "mConnectionEvents:\n" +
            "mGroupEvents:\n" +
            "mWifiP2pStatsProto.numPersistentGroup=0\n" +
            "mWifiP2pStatsProto.numTotalPeerScans=0\n" +
            "mWifiP2pStatsProto.numTotalServiceScans=0\n" +
            "mDppMetrics:\n" +
            "---Easy Connect/DPP metrics---\n" +
            "mWifiDppLogProto.numDppConfiguratorInitiatorRequests=0\n" +
            "mWifiDppLogProto.numDppEnrolleeInitiatorRequests=0\n" +
            "mWifiDppLogProto.numDppEnrolleeSuccess=0\n" +
            "mWifiDppLogProto.numDppR1CapableEnrolleeResponderDevices=0\n" +
            "mWifiDppLogProto.numDppR2CapableEnrolleeResponderDevices=0\n" +
            "mWifiDppLogProto.numDppR2EnrolleeResponderIncompatibleConfiguration=0\n" +
            "---End of Easy Connect/DPP metrics---\n" +
            "mWifiConfigStoreReadDurationHistogram:{1=2}\n" +
            "mWifiConfigStoreWriteDurationHistogram:{0=7, 5=2}\n" +
            "mLinkProbeSuccessRssiCounts:{}\n" +
            "mLinkProbeFailureRssiCounts:{}\n" +
            "mLinkProbeSuccessLinkSpeedCounts:{}\n" +
            "mLinkProbeFailureLinkSpeedCounts:{}\n" +
            "mLinkProbeSuccessSecondsSinceLastTxSuccessHistogram:{}\n" +
            "mLinkProbeFailureSecondsSinceLastTxSuccessHistogram:{}\n" +
            "mLinkProbeSuccessElapsedTimeMsHistogram:{}\n" +
            "mLinkProbeFailureReasonCounts:{}\n" +
            "mLinkProbeExperimentProbeCounts:{}\n" +
            "mNetworkSelectionExperimentPairNumChoicesCounts:{Pair{42902385 42330058}=NetworkSelectionExperimentResults{sameSelectionNumChoicesCounter={1=21}, differentSelectionNumChoicesCounter={}}, Pair{42598152 42330058}=NetworkSelectionExperimentResults{sameSelectionNumChoicesCounter={1=21}, differentSelectionNumChoicesCounter={}}, Pair{42504592 42330058}=NetworkSelectionExperimentResults{sameSelectionNumChoicesCounter={1=21}, differentSelectionNumChoicesCounter={}}}\n" +
            "mLinkProbeStaEventCount:0\n" +
            "mWifiNetworkRequestApiLog:\n" +
            "num_apps: 0\n" +
            "num_connect_success: 0\n" +
            "num_request: 0\n" +
            "num_user_approval_bypass: 0\n" +
            "num_user_reject: 0\n" +
            "\n" +
            "mWifiNetworkRequestApiMatchSizeHistogram:\n" +
            "{}\n" +
            "mWifiNetworkSuggestionApiLog:\n" +
            "num_connect_failure: 0\n" +
            "num_connect_success: 0\n" +
            "num_modification: 0\n" +
            "user_revoke_app_suggestion_permission: 0\n" +
            "\n" +
            "mWifiNetworkSuggestionApiMatchSizeHistogram:\n" +
            "{}\n" +
            "mWifiNetworkSuggestionApiAppTypeCounter:\n" +
            "{}\n" +
            "mUserApprovalSuggestionAppUiUserReaction:\n" +
            "mUserApprovalCarrierUiUserReaction:\n" +
            "mNetworkIdToNominatorId:\n" +
            "{0=2}\n" +
            "mWifiLockStats:\n" +
            "high_perf_active_time_ms: 0\n" +
            "low_latency_active_time_ms: 0\n" +
            "\n" +
            "mWifiLockHighPerfAcqDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiLockLowLatencyAcqDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiLockHighPerfActiveSessionDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiLockLowLatencyActiveSessionDurationSecHistogram:\n" +
            "{}\n" +
            "mWifiToggleStats:\n" +
            "num_toggle_off_normal: 0\n" +
            "num_toggle_off_privileged: 3\n" +
            "num_toggle_on_normal: 0\n" +
            "num_toggle_on_privileged: 7\n" +
            "\n" +
            "mWifiLogProto.numAddOrUpdateNetworkCalls=0\n" +
            "mWifiLogProto.numEnableNetworkCalls=0\n" +
            "mWifiLogProto.txLinkSpeedCount2g={1=1, 45=1, 52=622}\n" +
            "mWifiLogProto.txLinkSpeedCount5gLow={}\n" +
            "mWifiLogProto.txLinkSpeedCount5gMid={}\n" +
            "mWifiLogProto.txLinkSpeedCount5gHigh={}\n" +
            "mWifiLogProto.txLinkSpeedCount6gLow={}\n" +
            "mWifiLogProto.txLinkSpeedCount6gMid={}\n" +
            "mWifiLogProto.txLinkSpeedCount6gHigh={}\n" +
            "mWifiLogProto.rxLinkSpeedCount2g={52=621}\n" +
            "mWifiLogProto.rxLinkSpeedCount5gLow={}\n" +
            "mWifiLogProto.rxLinkSpeedCount5gMid={}\n" +
            "mWifiLogProto.rxLinkSpeedCount5gHigh={}\n" +
            "mWifiLogProto.rxLinkSpeedCount6gLow={}\n" +
            "mWifiLogProto.rxLinkSpeedCount6gMid={}\n" +
            "mWifiLogProto.rxLinkSpeedCount6gHigh={}\n" +
            "mWifiLogProto.numIpRenewalFailure=0\n" +
            "mWifiLogProto.connectionDurationStats=connectionDurationSufficientThroughputMs=0, connectionDurationInSufficientThroughputMs=0, connectionDurationCellularDataOffMs=1870500\n" +
            "mWifiLogProto.isExternalWifiScorerOn=false\n" +
            "mWifiLogProto.wifiOffMetrics=numWifiOff=3, numWifiOffDeferring=0, numWifiOffDeferringTimeout=0, wifiOffDeferringTimeHistogram={}\n" +
            "mWifiLogProto.softApConfigLimitationMetrics=numSecurityTypeResetToDefault=0, numMaxClientSettingResetToDefault=0, numClientControlByUserResetToDefault=0, maxClientSettingWhenReachHistogram={}\n" +
            "mChannelUtilizationHistogram2G:\n" +
            "{[75,100)=624}\n" +
            "mChannelUtilizationHistogramAbove2G:\n" +
            "{}\n" +
            "mTxThroughputMbpsHistogram2G:\n" +
            "{[25,50)=621, [100,150)=3}\n" +
            "mRxThroughputMbpsHistogram2G:\n" +
            "{[25,50)=621, [100,150)=3}\n" +
            "mTxThroughputMbpsHistogramAbove2G:\n" +
            "{}\n" +
            "mRxThroughputMbpsHistogramAbove2G:\n" +
            "{}\n" +
            "mCarrierWifiMetrics:\n" +
            "numConnectionSuccess=0, numConnectionAuthFailure=0, numConnectionNonAuthFailure0\n" +
            "mInitPartialScanTotalCount:\n" +
            "0\n" +
            "mInitPartialScanSuccessCount:\n" +
            "0\n" +
            "mInitPartialScanFailureCount:\n" +
            "0\n" +
            "mInitPartialScanSuccessHistogram:\n" +
            "{}\n" +
            "mInitPartialScanFailureHistogram:\n" +
            "{}\n" +
            "\n" +
            "Dump of WifiNetworkSuggestionsManager\n" +
            "WifiNetworkSuggestionsManager - Networks Begin ----\n" +
            "WifiNetworkSuggestionsManager - Networks End ----\n" +
            "WifiNetworkSuggestionsManager - Network Suggestions matching connection: null\n" +
            "\n" +
            "Dump of WifiBackupRestore\n" +
            "\n" +
            "ScoringParams: rssi2=-83:-80:-73:-60,rssi5=-80:-77:-70:-57,rssi6=-80:-77:-70:-57,pps=0:16:100,horizon=15,nud=8,expid=0\n" +
            "\n" +
            "WifiScoreReport:\n" +
            "time,session,netid,rssi,filtered_rssi,rssi_threshold,freq,txLinkSpeed,rxLinkSpeed,tx_good,tx_retry,tx_bad,rx_pps,nudrq,nuds,s1,s2,score\n" +
            "07-22 14:51:17.049,0,100,-30.0,-30.0,-83.0,2447,1,-1,0.00,0.00,0.00,0.63,0,1,93,103,60\n" +
            "07-22 15:24:13.400,7,102,-30.0,-30.0,-83.0,2447,52,52,0.64,0.00,0.00,0.42,0,3,93,103,60\n" +
            "\n" +
            "Dump of SarManager\n" +
            "isSarSupported: false\n" +
            "isSarVoiceCallSupported: false\n" +
            "isSarSoftApSupported: false\n" +
            "\n" +
            "\n" +
            "WifiNetworkScoreCache (android/1000)\n" +
            "  All score curves:\n" +
            "  Network scores for latest ScanResults:\n" +
            "    \"AndroidWifi\"02:15:b2:00:01:00: -128\n" +
            "\n" +
            "Dump of WifiSettingsConfigStore\n" +
            "Settings:\n" +
            "wifi_p2p_pending_factory_reset=false\n" +
            "wifi_scan_throttle_enabled=true\n" +
            "wifi_p2p_device_name=null\n" +
            "wifi_scan_always_enabled=true\n" +
            "wifi_verbose_logging_enabled=false\n" +
            "\n";

}