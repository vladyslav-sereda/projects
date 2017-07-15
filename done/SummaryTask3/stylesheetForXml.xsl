<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:st3="http://SummaryTask3.sereda.nure.ua/">


    <xsl:template match="st3:Cards">
        <xsl:apply-templates select="OldCard"/>
    </xsl:template>


    <xsl:template match="OldCard">
        <html>
            <head>
                <title>Cards</title>
                <style type="text/css">
                    td {
                    border: 1px
                    solid black;
                    padding: 5px
                    }
                    table{
                    border: 2px
                    solid green;
                    float: left;
                    }
                </style>
            </head>
            <table>
                <tr>
                    <td align="center" colspan="3">
                        <b>Old Card</b>
                    </td>
                </tr>
                <tr>
                    <td>Theme</td>
                    <td colspan="2">
                        <xsl:value-of select="Thema"/>
                    </td>
                </tr>
                <xsl:apply-templates select="Type"/>
                <tr>
                    <td>Country</td>
                    <td colspan="2">
                        <xsl:value-of select="Country"/>
                    </td>
                </tr>
                <tr>
                    <td>Year</td>
                    <td colspan="2">
                        <xsl:value-of select="Year"/>
                    </td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td colspan="2">
                        <xsl:choose>
                            <xsl:when test="Author">
                                <xsl:value-of select="Author"/>
                            </xsl:when>
                            <xsl:otherwise>
                                &#8212;
                            </xsl:otherwise>
                        </xsl:choose>
                    </td>
                </tr>
                <tr>
                    <td>Valuable</td>
                    <td colspan="2">
                        <xsl:value-of select="Valuable"/>
                    </td>
                </tr>
            </table>

        </html>
    </xsl:template>

    <xsl:template match="Type">
        <tr>
            <td>Type</td>
            <xsl:choose>
                <xsl:when test="@Send = 'true'">
                    <td>
                        <xsl:value-of select="TypeName"/>
                    </td>
                    <td>
                        Sended
                    </td>
                </xsl:when>
                <xsl:otherwise>
                    <td colspan="2">
                        <xsl:value-of select="TypeName"/>
                    </td>
                </xsl:otherwise>
            </xsl:choose>
        </tr>
    </xsl:template>
</xsl:stylesheet>