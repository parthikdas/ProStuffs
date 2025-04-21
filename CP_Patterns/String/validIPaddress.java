package CP_Patterns.String;
// 468. Validate IP Address
public class validIPaddress {
    public boolean isValidIPv4(String s) { // O(1), O(1)
        if (s.length() == 0 || (s.length() > 1 && s.startsWith("0"))) return false;
        try {
            int num = Integer.parseInt(s);
            return num >= 0 && num <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public boolean isValidIPv6(String s) {
        if (s.length() == 0 || s.length() > 4) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c) && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F')) {
                return false;
            }
        }
        return true;
    }
    public String validIPAddress(String IP) {
        if (IP.indexOf('.') != -1) {  // IPv4 check
            String[] parts = IP.split("\\.", -1);
            if (parts.length != 4) return "Neither";
            for (String part : parts) {
                if (!isValidIPv4(part)) return "Neither";
            }
            return "IPv4";
        } else if(IP.indexOf(':') != -1) { // chances for ipv6
                String[] parts = IP.split(":", -1);
                if (parts.length != 8) return "Neither";
                for (String part : parts) {
                    if (!isValidIPv6(part)) return "Neither";
                }
                return "IPv6";
        }
        return "Neither";
    }
}
