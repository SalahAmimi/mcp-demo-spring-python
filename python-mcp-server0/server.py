from mcp.server.fastmcp import FastMCP

mcp = FastMCP("Python MCP Server")

@mcp.tool()
def get_employee_info(name: str)->dict:
    """
    Get Information about a given employee name :
    - name
    - salary
    :param name:
    :return:
    """
    return {
        "employee_name" : name,
        "salary" : 5400,
    }